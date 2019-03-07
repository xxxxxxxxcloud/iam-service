package io.choerodon.iam.api.controller.v1;

import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.iam.api.dto.ApplicationDTO;
import io.choerodon.iam.app.service.ApplicationService;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.CustomPageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * @author superlee
 * @since 0.15.0
 **/
@RestController
@RequestMapping(value = "/v1/organizations/{organization_id}/applications")
public class ApplicationController {

    private ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "创建应用")
    @PostMapping
    public ResponseEntity<ApplicationDTO> create(@PathVariable("organization_id") Long organizationId,
                                                 @RequestBody @Valid ApplicationDTO applicationDTO) {
        applicationDTO.setOrganizationId(organizationId);
        return new ResponseEntity<>(applicationService.create(applicationDTO), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "更新应用")
    @PostMapping("/{id}")
    public ResponseEntity<ApplicationDTO> update(@PathVariable("organization_id") Long organizationId,
                                                 @PathVariable("id") Long id,
                                                 @RequestBody @Valid ApplicationDTO applicationDTO) {
        applicationDTO.setOrganizationId(organizationId);
        applicationDTO.setId(id);
        return new ResponseEntity<>(applicationService.update(applicationDTO), HttpStatus.OK);
    }


    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "分页查询应用")
    @CustomPageRequest
    @GetMapping
    public ResponseEntity<Page<ApplicationDTO>> pagingQuery(@ApiIgnore
                                                            @SortDefault(value = "id", direction = Sort.Direction.ASC) PageRequest pageRequest,
                                                            ApplicationDTO applicationDTO) {
        return new ResponseEntity<>(applicationService.pagingQuery(pageRequest, applicationDTO), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "启用应用")
    @PutMapping(value = "/{id}/enable")
    public ResponseEntity<ApplicationDTO> enabled(@PathVariable Long id) {
        return new ResponseEntity<>(applicationService.enable(id), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "禁用应用")
    @PutMapping(value = "/{id}/disable")
    public ResponseEntity<ApplicationDTO> disable(@PathVariable Long id) {
        return new ResponseEntity<>(applicationService.disable(id), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "获取application的类型列表")
    @GetMapping(value = "/types")
    public ResponseEntity<List<String>> types() {
        return new ResponseEntity<>(applicationService.types(), HttpStatus.OK);

    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "重名校验")
    @PostMapping("/check")
    public ResponseEntity check(@RequestBody ApplicationDTO applicationDTO) {
        applicationService.check(applicationDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}