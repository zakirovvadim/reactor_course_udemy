package ru.vadim.java.reactive.cource.tutorial.sec13.client;


import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {
    public static final Map<String, String> USER_CATEGORY = Map.of("vadim", "standart", "nastya", "plus"); // список пользователей и их категории

    static Function<Context, Context> userCategoryContext() {
        return ctx -> ctx.<String>getOrEmpty("user")//достаем пользователя из контекста
                .filter(USER_CATEGORY::containsKey) // фильтруем по наличию ключа
                .map(USER_CATEGORY::get) // мапим в значение - т .е .в категорию
                .map(category -> ctx.put("category", category)) // кладем эту категорию в контекст под одноименный ключ
                .orElse(Context.empty()); // иначе вычищаем контекст
    }
}
