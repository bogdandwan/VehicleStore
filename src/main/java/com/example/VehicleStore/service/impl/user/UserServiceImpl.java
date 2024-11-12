package com.example.VehicleStore.service.impl.user;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.entity.user.sort.UserSort;
import com.example.VehicleStore.repository.user.UserRepository;
import com.example.VehicleStore.search.user.UserSearch;
import com.example.VehicleStore.search.user.spec.UserSpec;
import com.example.VehicleStore.service.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll(UserSearch search, Pagination pagination, UserSort sort) {
        return userRepository.findAll(new UserSpec(search), pagination.pageable(buildSort(sort)));
    }

    private Sort buildSort(UserSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }

        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case FIRST_NAME_ASC, FIRST_NAME_DESC -> "firstName";
            case LAST_NAME_ASC, LAST_NAME_DESC -> "lastName";
            case EMAIL_ASC, EMAIL_DESC -> "email";
            case ROLE_ASC, ROLE_DESC -> "role";
            default -> "id";
        };

        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }

    @Override
    public void softDelete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setDeletionTime(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
