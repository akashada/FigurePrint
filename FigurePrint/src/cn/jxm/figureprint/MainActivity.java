package cn.jxm.figureprint;

import cn.jxm.service.FigurePrintService;
import cn.jxm.service.FigurePrintServiceFactory;
import cn.jxm.service.FigurePrintServiceType;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private FigurePrintService figurePrintService = FigurePrintServiceFactory.createFigurePrintService(FigurePrintServiceType.FPM30A);

	
	private TextView pageId;
	
	private final static int SEARCH = 0;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SEARCH:
				int recv = figurePrintService.searchBy1_N();
//				int recv = figurePrintService.getFigureNum();
//				int recv = figurePrintService.addFigure();
				pageId.setText("PageId: " + recv);
				break;

			default:
				break;
			}
		}
		
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        figurePrintService = FigurePrintServiceFactory.createFigurePrintService(FigurePrintServiceType.FPM30A);

        pageId = (TextView) findViewById(R.id.textViewPageId);
        
        Button buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Message msg = new Message();
				msg.what = SEARCH;
				handler.sendMessage(msg);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
