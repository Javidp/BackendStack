package com.jd.backend.rest.model.users;

import org.springframework.data.jpa.repository.JpaRepository;

interface UsersRepository extends JpaRepository<User, Long> {
}
