package com.demo.order_service.external;

public record UserDto(
        Long userId,
        String name,
        String email
) {
}
