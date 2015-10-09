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

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class ExpandActivity extends AppCompatActivity implements View.OnClickListener {

	String quote;
	String[] quotes;
	Random wheel;
	TextView quoteText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expand);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnNew);
		fab.setOnClickListener(this);

		quoteText = (TextView) findViewById(R.id.txtQuote);
		quoteText.setTypeface(Typeface.createFromAsset(getAssets(), "GI-Regular.otf"));

		wheel = new Random();
		quotes = getResources().getStringArray(R.array.quotes);

		quote = this.getIntent().getStringExtra("net.logomancy.dashquotes.civ5.QuoteString");
		if(quote == null) {
			quote = getQuote();
		}
		quoteText.setText(quote);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case (R.id.menu_copy):
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				ClipData copypasta = ClipData.newPlainText("Civ V Quote", quoteText.getText());
				clipboard.setPrimaryClip(copypasta);
				Snackbar.make(quoteText, R.string.sys_copied, Snackbar.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// boilerplate
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	protected String getQuote() {
		return quotes[wheel.nextInt(quotes.length)];
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case (R.id.btnNew):
				quoteText.setText(getQuote());
				break;
			default:
				break;
		}
	}
}
