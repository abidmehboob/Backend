package com.alseyahat.app.security.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import com.alseyahat.app.feature.customer.repository.entity.Customer;
import com.alseyahat.app.feature.employee.repository.entity.Employee;
import com.alseyahat.app.feature.employee.repository.entity.QEmployeeRole;
import com.alseyahat.app.feature.employee.service.EmployeeRoleService;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class AuthenticationTokenEnhancer extends JwtAccessTokenConverter {
	
    ModelMapper modelMapper;
    
    @Autowired
    EmployeeRoleService employeeRoleService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        if (authentication.getPrincipal() instanceof Employee) {
            final Employee employee = (Employee) authentication.getPrincipal();
            additionalInfo.put("type", "employee");
            additionalInfo.put("id", employee.getEmployeeId());
            additionalInfo.put("security_key", employee.getSecurityKey());
            additionalInfo.put("security_aes_key", employee.getAesKey());
            additionalInfo.put("name", employee.getName());
            additionalInfo.put("email", employee.getEmail());
            additionalInfo.put("roles", employeeRoleService.findOne(QEmployeeRole.employeeRole.roleId.eq(Long.valueOf(employee.getRoleId()))));
//            additionalInfo.put("branches", employee.getBranches().stream().map(Branch::getBranchId).toArray());
        }
        if (authentication.getPrincipal() instanceof Customer) {
            final Customer customer = (Customer) authentication.getPrincipal();
            additionalInfo.put("type", "customer");
            additionalInfo.put("id", customer.getCustomerId());
            additionalInfo.put("security_key", customer.getSecurityKey());
            additionalInfo.put("security_aes_key", customer.getAesKey());
            additionalInfo.put("name", customer.getName());
//            ofNullable(customer.getBranch()).ifPresent(branchId -> additionalInfo.put("branchId", branchId.getBranchId()));
        }
        accessToken = super.enhance(accessToken, authentication);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
    
//    private List<RolesResponse> buildRoleResponse(final List<EmployeeBranchRoles> employeeBranchRoleslst) {
//    	List<RolesResponse> roles= new ArrayList<RolesResponse>();
//    	for(EmployeeBranchRoles employeeBranchRoles : employeeBranchRoleslst) {
//    		if(STORE_SUPER_ADMIN.equals(employeeBranchRoles.getRole().getName())) {
//    			final List<Branch> branches = branchService.findAll(QBranch.branch.store.storeId.eq(employeeBranchRoles.getStoreId()));
//    			branches.stream().forEach(branch -> {
//    				employeeBranchRoles.setBranchId(branch.getBranchId());
//    	    		roles.add(buildRole(employeeBranchRoles));
//    			});
//    		}else {
//        		roles.add(buildRole(employeeBranchRoles));
//    		}
//       }
//   	return roles;
//    }
    
//    private RolesResponse buildRole(final EmployeeBranchRoles employeeBranchRoles) {
//    	RolesResponse response = new RolesResponse();
//    	if(StringUtils.isNotEmpty(employeeBranchRoles.getBranchId())) {
//    		  response.setBranchId(employeeBranchRoles.getBranchId());
//     	}
//    	if(StringUtils.isNotEmpty(employeeBranchRoles.getStoreId())) {
//     		 response.setStoreId(employeeBranchRoles.getStoreId());	
//    	}
//        response.setName(employeeBranchRoles.getRole().getName());
//        response.setRoleId(String.valueOf(employeeBranchRoles.getRole().getRoleId()));
//        response.setType(employeeBranchRoles.getRole().getType());
//        return response;
//    }
}
