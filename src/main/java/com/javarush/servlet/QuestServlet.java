package com.javarush.servlet;

import com.javarush.exception.InvalidQuestStateException;
import com.javarush.model.Option;
import com.javarush.model.OptionType;
import com.javarush.model.QuestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(QuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);

        if ("true".equals(req.getParameter("newGame"))) {
            session.setAttribute("node-index", 0);
        }

        String playerName = req.getParameter("playerName");
        if (playerName != null) {
            session.setAttribute("player-name", playerName);
        }

        getServletContext().getRequestDispatcher("/quest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        logger.info("Retrieving info from selected option");
        int currentNodeIndex = (int) session.getAttribute("node-index");
        int selectedOptionIndex = Integer.parseInt(req.getParameter("option"));

        Option selectedOption = QuestInfo.getOptionFromNodes(currentNodeIndex, selectedOptionIndex);
        String questResult = QuestInfo.getQuestResult(selectedOption);
        OptionType selectedOptionType = selectedOption.getOptionType();

        RequestDispatcher requestDispatcher;

        switch (selectedOptionType) {
            case WIN -> {
                logger.info("Player won, wins count increased");
                int wins = (int) session.getAttribute("wins");
                session.setAttribute("wins", wins + 1);
                int gamesPlayed = (int) session.getAttribute("games-played");
                session.setAttribute("games-played", gamesPlayed + 1);
                requestDispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
            }
            case LOSS -> {
                logger.info("Player lost, losses count increased");
                int losses = (int) session.getAttribute("losses");
                session.setAttribute("losses", losses + 1);
                int gamesPlayed = (int) session.getAttribute("games-played");
                session.setAttribute("games-played", gamesPlayed + 1);
                requestDispatcher = getServletContext().getRequestDispatcher("/finish.jsp");
            }
            case REGULAR -> {
                logger.info("Making transition to other option node");
                int nextIndex = selectedOption.getNextOptionNodeIndex();
                if (nextIndex >= 0 && nextIndex < QuestInfo.getOptionNodes().size()) {
                    session.setAttribute("node-index", nextIndex);
                    requestDispatcher = getServletContext().getRequestDispatcher("/quest.jsp");
                }
                else {
                    logger.error("Invalid node index");
                    throw new InvalidQuestStateException("Invalid node index");
                }
            }
            default -> {
                logger.error("Invalid option type");
                throw new InvalidQuestStateException("Invalid option type");
            }
        }
        req.setAttribute("quest-result", questResult);
        requestDispatcher.forward(req, resp);
    }
}