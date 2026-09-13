package com.papercheck;

import com.papercheck.core.PaperSimilarity;
import com.papercheck.exception.PaperCheckException;
import com.papercheck.io.AnswerWriter;
import com.papercheck.io.CommandLineParser;
import com.papercheck.io.FileTextReader;

import java.nio.file.Paths;

/**
 * 程序入口。
 *
 * <p>用法：java -jar main.jar [原文文件绝对路径] [抄袭版论文文件绝对路径] [答案文件绝对路径]</p>
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        try {
            CommandLineParser.validate(args);

            String originalText = FileTextReader.read(Paths.get(args[0]));
            String plagiarizedText = FileTextReader.read(Paths.get(args[1]));

            PaperSimilarity checker = new PaperSimilarity();
            double similarity = checker.compute(originalText, plagiarizedText);

            AnswerWriter.write(Paths.get(args[2]), similarity);
        } catch (PaperCheckException | IllegalArgumentException e) {
            System.err.println("[错误] " + e.getMessage());
            System.exit(1);
        }
    }
}
