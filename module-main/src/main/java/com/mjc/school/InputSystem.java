package com.mjc.school;

import com.mjc.school.controller.commands.impl.authorCommand.*;
import com.mjc.school.controller.commands.impl.newsCommand.*;
import com.mjc.school.controller.commands.impl.tagCommand.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

@Component
public class InputSystem {
    private static final String COMMANDS = """
            Enter the number of operation:
            1 - Get all news.
            2 - Get news by id.
            3 - Create news.
            4 - Update news.
            5 - Remove news by id.
            6 - Get all author.
            7 - Get authors by id.
            8 - Create author.
            9 - Update author.
            10 - Remove author by id.
            11 - Get all tags.
            12 - Get tags by id.
            13 - Create tag.
            14 - Update tag.
            15 - Remove tag by id.
            16 - Get news by Tag name.
            17 - Get news by Tag id.
            18 - Get news by Author name.
            19 - Get news by title.
            20 - Get news by content.
            21 - Get tags by News id.
            22 - Get authors by News id.
            0 - Exit.
            """;
    @Autowired
    private ApplicationContext context;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(COMMANDS);

                int num = Integer.parseInt(scanner.nextLine());
                switch (num) {
                    case 1 -> context.getBean("1", ReadAllNewsCommand.class).execute();
                    case 2 -> context.getBean("2", ReadByIdNewsCommand.class).execute();
                    case 3 -> context.getBean("3", CreateNewsCommand.class).execute();
                    case 4 -> context.getBean("4", UpdateNewsCommand.class).execute();
                    case 5 -> context.getBean("5", DeleteNewsCommand.class).execute();
                    case 6 -> context.getBean("6", ReadAllAuthorCommand.class).execute();
                    case 7 -> context.getBean("7", ReadByIdAuthorCommand.class).execute();
                    case 8 -> context.getBean("8", CreateAuthorCommand.class).execute();
                    case 9 -> context.getBean("9", UpdateAuthorCommand.class).execute();
                    case 10 -> context.getBean("10", DeleteAuthorCommand.class).execute();
                    case 11 -> context.getBean("11", ReadAllTagCommand.class).execute();
                    case 12 -> context.getBean("12", ReadByIdTagCommand.class).execute();
                    case 13 -> context.getBean("13", CreateTagCommand.class).execute();
                    case 14 -> context.getBean("14", UpdateTagCommand.class).execute();
                    case 15 -> context.getBean("15", DeleteTagCommand.class).execute();
                    case 16 -> context.getBean("16", ReadNewsByTagNameCommand.class).execute();
                    case 17 -> context.getBean("17", ReadNewsByTagIdCommand.class).execute();
                    case 18 -> context.getBean("18", ReadNewsByAuthorNameCommand.class).execute();
                    case 19 -> context.getBean("19", ReadNewsByTitleCommand.class).execute();
                    case 20 -> context.getBean("20", ReadNewsIdContentCommand.class).execute();
                    case 21 -> context.getBean("21", ReadTagByNewsIdCommand.class).execute();
                    case 22 -> context.getBean("22", ReadAuthorByNewsIdCommand.class).execute();
                    case 0 -> System.exit(1);
                }

            } catch (RuntimeException | IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
