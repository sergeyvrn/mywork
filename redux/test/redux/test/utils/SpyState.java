package redux.test.utils;

import redux.Action;
import redux.Reducer;
import redux.State;

import java.util.ArrayList;

class SpyAction implements Action {
    final String type;
    SpyAction(String type) {
        this.type = type;
    }
}

public class SpyState implements State {
    private ArrayList<String> actionLog;

    public SpyState() {
        actionLog = new ArrayList<>();
    }

    private SpyState(ArrayList<String> data) {
        actionLog = data;
    }

    public ArrayList<String> getActionLog() {
        return new ArrayList<>(actionLog);
    }

    public static Action action(String type) {
        return new SpyAction(type);
    }
    public static Reducer reducer = (state, action) -> {
        SpyState spyState = (SpyState) state;
        ArrayList<String> actionLog = spyState.getActionLog();
        SpyAction spyAction = (SpyAction) action;
        actionLog.add(((SpyAction) action).type);
        return new SpyState(actionLog);
    };
}
