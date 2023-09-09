package com.mjc.school.controller.commands.impl.newsCommand;

import com.mjc.school.controller.MessageHandler;
import com.mjc.school.controller.NewsCommandsController;
import com.mjc.school.controller.commands.Command;
import com.mjc.school.service.dto.NewsDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

@Component("20")
public class ReadNewsIdContentCommand implements Command {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    @Qualifier("newsController")
    private NewsCommandsController<NewsDTOResponse, Long> controller;

    @Override
    public void execute() throws InvocationTargetException, IllegalAccessException {
        Method method = MessageHandler.get(20);
        System.out.println("Operation: Get news by content.\n" + "Enter news content:");
        String content = scanner.nextLine();
        method.invoke(controller, content);
    }
}
