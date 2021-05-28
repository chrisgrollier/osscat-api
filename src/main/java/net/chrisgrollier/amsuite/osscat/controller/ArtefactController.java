package net.chrisgrollier.amsuite.osscat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import net.chrisgrollier.amsuite.osscat.model.ArtefactInfoView;
import net.chrisgrollier.amsuite.osscat.model.ErrorView;
import net.chrisgrollier.amsuite.osscat.model.ReadOnlyArtefactView;
import net.chrisgrollier.amsuite.osscat.model.SimpleArtefactVersionView;
import net.chrisgrollier.amsuite.osscat.model.SimpleArtefactView;
import net.chrisgrollier.amsuite.osscat.model.UpdatableArtefactView;
import net.chrisgrollier.amsuite.osscat.model.VersionInfoView;
import net.chrisgrollier.amsuite.osscat.service.ArtefactViewService;
import net.chrisgrollier.amsuite.osscat.service.CreateOrUpdateResult;

@RestController
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth", scopes = { "read" })
public class ArtefactController {

	@Autowired
	private ArtefactViewService artefactViewService;

	public ArtefactController() {
	}

	@PutMapping("artefacts/{id}")
	@SecurityRequirement(name = "bearerAuth", scopes = { "read", "create" })
	@Operation(summary = "Update or create artefact identified by its natural id", description = "Return a single artefact", tags = "artefact")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Artefact was updated"),
			@ApiResponse(responseCode = "201", description = "Artefact was created"),
			@ApiResponse(responseCode = "400", content = {
					@Content(schema = @Schema(implementation = ErrorView.class)) }) })
	public ResponseEntity<ReadOnlyArtefactView> createOrUpdateArtefact(
			@Parameter(example = "mvn:org.springframework.boot:spring-boot:jar", description = "Natural id of the artefact. Is actually concatenation of kind, name and type of the artefact separated by colons")
			@PathVariable String id,
			@RequestBody UpdatableArtefactView artefactView) {
		CreateOrUpdateResult<ReadOnlyArtefactView> result = this.artefactViewService.createOrUpdateArtefact(id,
				artefactView);
		return new ResponseEntity<>(result.getResult(), result.isCreated() ? HttpStatus.CREATED : HttpStatus.OK);
	}

	@PutMapping("artefacts/{idPart1}/idPart2")
	@Operation(hidden = true, tags = "artefact")
	@SecurityRequirement(name = "bearerAuth", scopes = { "read", "write", "create" })
	public ResponseEntity<ReadOnlyArtefactView> createOrUpdateNpmScopedArtefact(@PathVariable String idPart1,
			@PathVariable String idPart2, @RequestBody UpdatableArtefactView artefactView) {
		CreateOrUpdateResult<ReadOnlyArtefactView> result = this.artefactViewService
				.createOrUpdateArtefact(idPart1 + '/' + idPart2, artefactView);
		return new ResponseEntity<>(result.getResult(), result.isCreated() ? HttpStatus.CREATED : HttpStatus.OK);
	}

	@GetMapping("/artefacts/{id}")
	@SecurityRequirement(name = "bearerAuth", scopes = { "read" })
	@Operation(summary = "Find artefact by natural id", description = "Return a single artefact", tags = "artefact")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", content = {
					@Content(schema = @Schema(implementation = ErrorView.class)) }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(ref = "ErrorView"), examples = {
					@ExampleObject(value = "{\"error\":\"artefact with id 'mvn:org.test:test:jar' not found.\", \"type\":\"resourcenotfoundexception\"}") }) }) })
	public ReadOnlyArtefactView getArtefactById(
			@Parameter(example = "npm:tslib:js", description = "Natural id of the artefact. Is actually concatenation of kind, name and type of the artefact separated by colons")
			@PathVariable String id) {
		return this.artefactViewService.findArtefactViewById(id);
	}

	@GetMapping("/artefacts/{idPart1}/{idPart2}")
	@SecurityRequirement(name = "bearerAuth", scopes = { "read" })
	@Operation(hidden = true, tags = "artefact")
	public ReadOnlyArtefactView getNpmScopedArtefactById(@PathVariable String idPart1, @PathVariable String idPart2) {
		return this.artefactViewService.findArtefactViewById(idPart1 + '/' + idPart2);
	}

	@GetMapping("/artefacts/count")
	public long getCount() {
		return this.artefactViewService.countArtefact();
	}

	@GetMapping("/artefacts/simple")
	public List<SimpleArtefactView> getAllSimple() {
		return this.artefactViewService.getAllSimple();
	}

	@PatchMapping("/artefacts/{id}")
	@SecurityRequirement(name = "bearerAuth", scopes = { "read", "write" })
	public SimpleArtefactView updateArtefactView(@PathVariable String id, @RequestBody ArtefactInfoView textInfo) {
		return this.artefactViewService.patchArtefactTextInfo(id, textInfo);
	}

	@GetMapping("/artefacts")
	public List<ReadOnlyArtefactView> findReportableArtefactViews(@RequestParam(defaultValue = "mvn") String kind,
			@RequestParam(required = false) String[] names, @RequestParam(required = false) String group) {
		return this.artefactViewService.findReportableArtefactViews(kind, names, group);
	}

	@PatchMapping("/versions/{id}")
	public SimpleArtefactVersionView updateArtefactVersionView(@PathVariable String id,
			@RequestBody VersionInfoView versionInfo) {
		return this.artefactViewService.patchArtefactVersionTextInfo(id, versionInfo);
	}
}
