// DashQuotes: Civilization V, Copyright 2013 Andrew Mike <logomancer@gmail.com>.
//
// This file is part of DashQuotes: Civilization V.
//
// DashQuotes: Civilization V is free software you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// DashQuotes: Civilization V is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// For the GPLv3 license terms, see the LICENSE file or go to <http://www.gnu.org/licenses/>.

package net.logomancy.dashquotes.civ5;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.logomancy.dashquotes.civ5.R;

public class ExpandActivity extends Activity implements OnClickListener {

	String quote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.expand);
		
		quote = this.getIntent().getStringExtra("net.logomancy.dashquotes.civ5.QuoteString");
		TextView quoteText = (TextView) findViewById(R.id.txtQuote);
		quoteText.setText(quote);
		
		Button close = (Button) findViewById(R.id.btnClose);
		close.setOnClickListener(this);
		
		Button cp = (Button) findViewById(R.id.btnCopy);
		cp.setOnClickListener(this);
		
	}
	
	public void onClick(View v) {
		switch(v.getId()){
		case(R.id.btnClose):
			finish();
		break;
		case(R.id.btnCopy):
			ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			ClipData copypasta = ClipData.newPlainText("Civ V Quote", quote);
			clipboard.setPrimaryClip(copypasta);
			Toast.makeText(this, R.string.sys_copied, Toast.LENGTH_SHORT).show();
		break;
		}
	}
}
