@echo off
javac -d out src\Main.java src\CommandParser.java src\TaskRepository.java src\TaskService.java src\Task.java src\JsonUtils.java

java -cp out Main %*
