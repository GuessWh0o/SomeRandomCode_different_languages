import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

/**
 * Created by guessWh0o on 06.12.2017.
 */

public class CustomSnackBar {

    public CustomSnackBar(View idViewToRender, String text, String actionButtonText, int length) {
        Snackbar snackbar = Snackbar
                .make(idViewToRender, text, length)
                .setAction(actionButtonText, view -> {

                });

        // Changing message text color
        snackbar.setActionTextColor(Color.GREEN);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.show();
    }

    public CustomSnackBar(View idViewToRender, String text, String actionButtonText, int length, CustomSnackBar.ActionsEnum action, Context context) {
        Snackbar snackbar = Snackbar
                .make(idViewToRender, text, length)
                .setAction(actionButtonText, view -> {
                    if(action != null) {
                        if(action.equals(ActionsEnum.restartApp)) {
                            restartTheApp();
                        } else if(action.equals(ActionsEnum.startActivity)) {
                            startDosar(context);
                        }
                    }
                });

        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.Black6));
        // Changing message text color
        snackbar.setActionTextColor(Color.GREEN);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public CustomSnackBar(View idViewToRender, String text, String actionButtonText, int length, CustomSnackBar.ActionsEnum action, Context context, String nrDeConstatare) {
        Snackbar snackbar = Snackbar
                .make(idViewToRender, text, length)
                .setAction(actionButtonText, view -> {
                    if(action != null) {
                        if(action.equals(ActionsEnum.restartApp)) {
                            restartTheApp();
                        } else if(action.equals(ActionsEnum.startDosar)) {
                            startActivity(context);
                        }
                    }
                });

        View snackView = snackbar.getView();
        snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.Black6));
        // Changing message text color
        snackbar.setActionTextColor(Color.GREEN);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public enum ActionsEnum {
        restartApp("RESTART"),
        startActivity("START_ACTIVITY");

        private String key = null;

        ActionsEnum(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }
    }

    private void restartTheApp() {
        Intent i = getApplicationContext().getPackageManager()
                .getLaunchIntentForPackage(getApplicationContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getApplicationContext().startActivity(i);
    }


    private void startActivity(Context context) {
        Intent intent = new Intent(context, Activity1.class);
        intent.putExtra(Constants.SOME_STUFF, "DA");
        context.startActivity(intent);
    }

}
