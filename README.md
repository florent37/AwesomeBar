# AwesomeBar

[![screen](https://raw.githubusercontent.com/florent37/AwesomeBar/master/media/awesomebar.gif)](https://www.github.com/florent37/AwesomeBar)

# Usage

```xml
<com.github.florent37.awesomebar.AwesomeBar
            android:id="@+id/bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/white"
            android:elevation="4dp" />
```

```java
bar.addAction(R.drawable.awsb_ic_edit_animated, "Compose");

bar.setActionItemClickListener(new AwesomeBar.ActionItemClickListener() {
    @Override
    public void onActionItemClicked(int position, ActionItem actionItem) {
        Toast.makeText(getBaseContext(), actionItem.getText()+" clicked", Toast.LENGTH_LONG).show();
    }
});

bar.setOnMenuClickedListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        drawerLayout.openDrawer(Gravity.START);
    }
});
```

#Download

In your module [![Download](https://api.bintray.com/packages/florent37/maven/AwesomeBar/images/download.svg)](https://bintray.com/florent37/maven/AwesomeBar/_latestVersion)
```groovy
compile 'com.github.florent37:awesomebar:1.0.0'
```

#Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/in/florentchampigny">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2017 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
