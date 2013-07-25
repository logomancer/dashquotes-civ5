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

import android.content.Intent;
import android.content.res.Resources;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;
import java.util.Random;
import net.logomancy.dashquotes.civ5.R;

public class Civ5QuotesExtension extends DashClockExtension {
	String[] quotes;
	Random wheel;
	Resources res;
	
	@Override
	public void onInitialize(boolean isReconnect) {
		res = getResources();
		wheel = new Random();
		quotes = res.getStringArray(R.array.quotes);
		setUpdateWhenScreenOn(true);	
	}

	@Override
	protected void onUpdateData(int reason) {
		Integer index = wheel.nextInt(quotes.length);
		
		Intent expand = new Intent();
		expand.setClassName(this, "net.logomancy.dashquotes.civ5.ExpandActivity");
		expand.putExtra("net.logomancy.dashquotes.civ5.QuoteString", quotes[index]);
		
		publishUpdate(new ExtensionData()
        .visible(true)
        .icon(R.drawable.ic_launcher)
        .status(res.getString(R.string.sys_short_title))
        .expandedTitle(res.getString(R.string.app_name))
        .expandedBody(quotes[index])
        .clickIntent(expand));
	}
}