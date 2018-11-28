package com.edu.sjsu.cs.cs151;

import sun.plugin2.message.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private View view;
    private Model model;
    private HoldBlockView nextBlockView;
    private BlockingQueue<Message> messageQueue;
    private List<Valve> valves = new LinkedList<Valve>();
    Model.NextTetrominoGenerator nextTetrominoGenerator;
    private MainGameView mainGameView;

    public Controller(View view, Model model)
    {
        this.view = view;
        this.model = model;
        nextTetrominoGenerator = model.new NextTetrominoGenerator();
        nextBlockView = view.mainGameView.getNextBlock();
        nextBlockView.inputTetromino(nextTetrominoGenerator.generateRandom());
        mainGameView = view.getMainGameView();
    }

    private class DoNewGameValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoHardDropValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoSoftDropValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoLeftValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoRightValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message) {
            return null;
        }
    }

    private class DoRotateValve implements Valve
    {

        @Override
        public ValveResponse execute(Message message)
        {
//            if (message.getClass() != RotateMessage.class)
//            {
//                return ValveResponse.MISS;
//            }

            Model.Tetromino tester = nextTetrominoGenerator.generateRandom();
            tester.rotate();

            return ValveResponse.EXECUTED;
        }
    }

    public void mainLoop() throws Exception
    {
        /**
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while(response != ValveResponse.FINISH)
        {
            try
            {
                message = (Message)messageQueue.take();
            }

            catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            for(Valve valve : valves)
            {
                response = valve.execute(message);
                if (response != ValveResponse.MISS)
                {
                    break;
                }
            }
        }
         */
        //give a new model tetromino to nextBlockView Object

        // get reference to block
        Model.Tetromino tester = nextTetrominoGenerator.generateRandom();
        tester.moveTetromino(4, 0);


        //spawn at grid (4,20) is center
        mainGameView.getGameGrid().spawnTetromino(tester);

        //create a timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Jeffrey smells");
                tester.rotate();
                System.out.println(tester.getCoordinates()[0].getX());
            }
        },0,1000);
    }
}
