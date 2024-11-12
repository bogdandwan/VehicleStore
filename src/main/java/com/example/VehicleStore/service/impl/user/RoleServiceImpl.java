package com.example.VehicleStore.service.impl.user;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.user.Role;
import com.example.VehicleStore.entity.user.sort.RoleSort;
import com.example.VehicleStore.repository.user.RoleRepository;
import com.example.VehicleStore.search.user.RoleSearch;
import com.example.VehicleStore.search.user.spec.RoleSpec;
import com.example.VehicleStore.service.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService{

    final RoleRepository roleRepository;


    @Override
    @Transactional(readOnly = true)
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> findAll(RoleSearch search, Pagination pagination, RoleSort sort) {
        return roleRepository.findAll(new RoleSpec(search), pagination.pageable(buildSort(sort)));
    }

    private Sort buildSort(RoleSort sort) {
        if (sort == null){
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort){
            case NAME_ASC, NAME_DESC -> "name";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC: Sort.Direction.DESC,property);
    }

    @Override
    public void softDelete(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null){
            role.setDeletionTime(LocalDateTime.now());
            roleRepository.save(role);
        }
    }
}
