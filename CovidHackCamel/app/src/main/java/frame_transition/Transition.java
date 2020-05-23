package frame_transition;

import android.content.Context;
import android.content.Intent;

public class Transition {
    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    private Intent intent;
    private Context ctx;

    public Transition(Context ctx) {
        this.ctx = ctx;
    }

    public void goTo(Class cname) {
        intent = new Intent(ctx, cname);
        ctx.startActivity(intent);
    }
}
