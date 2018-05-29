package io.choerodon.iam.domain.repository;

import io.choerodon.core.domain.Page;
import io.choerodon.iam.api.dto.RoleAssignmentSearchDTO;
import io.choerodon.iam.domain.iam.entity.UserE;
import io.choerodon.iam.infra.dataobject.UserDO;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;

import java.util.List;

/**
 * @author dongfan117@gmail.com
 * @author superlee
 */
public interface UserRepository {

    UserE selectByLoginName(String loginName);

    int selectCount(UserDO user);

    UserE insertSelective(UserE userE);

    Page<UserDO> pagingQuery(PageRequest pageRequest, UserDO userDO, String[] params);

    UserE selectByPrimaryKey(Long id);

    UserE updateSelective(UserE userE);

    void deleteById(Long id);

    Page<UserDO> pagingQueryUsersWithSiteLevelRoles(
            PageRequest pageRequest, RoleAssignmentSearchDTO roleAssignmentSearchDTO);

    Page<UserDO> pagingQueryUsersWithOrganizationLevelRoles(
            PageRequest pageRequest, RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long sourceId);

    Page<UserDO> pagingQueryUsersWithProjectLevelRoles(
            PageRequest pageRequest, RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long sourceId);

    UserE updateUserInfo(UserE userE);

    UserDO selectOne(UserDO user);

    Page<UserDO> pagingQueryWhoBelongsToTheProject(Long projectId, PageRequest pageRequest, String param);

    Integer selectUserCountFromMemberRoleByOptions(Long roleId, String memberType, Long sourceId,
                                                   String sourceType, RoleAssignmentSearchDTO roleAssignmentSearchDTO);

    Page<UserDO> pagingQueryUsersByRoleIdOnSiteLevel(PageRequest pageRequest,
                                                     RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long roleId);

    List<UserDO> listUsersByRoleIdOnSiteLevel(Long roleId);

    List<UserDO> listUsersByRoleIdOnOrganizationLevel(Long orgId, Long roleId);

    List<UserDO> listUsersByRoleIdOnProjectLevel(Long proId, Long roleId);

    Page<UserDO> pagingQueryUsersByRoleIdOnOrganizationLevel(PageRequest pageRequest,
                                                             RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long roleId, Long sourceId);

    Page<UserDO> pagingQueryUsersByRoleIdOnProjectLevel(PageRequest pageRequest,
                                                        RoleAssignmentSearchDTO roleAssignmentSearchDTO, Long roleId, Long sourceId);

    List<UserDO> listUsersByRoleId(Long roleId, String memberType, String sourceType);

    Page<UserDO> pagingQueryAdminUsers(PageRequest pageRequest);

    /**
     * 根据用户id集合查询用户的集合
     * @param ids 用户id数组
     * @return List<UserDO> 用户集合
     */
    List<UserDO> listUsersByIds(Long[] ids);
}
