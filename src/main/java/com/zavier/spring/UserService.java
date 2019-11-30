package com.zavier.spring;

import java.util.concurrent.Future;

public interface UserService {
    Future<String> printUser();
}
