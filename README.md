# AwesomeBar


<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>


[![screen](https://raw.githubusercontent.com/florent37/AwesomeBar/master/media/awesomebar.gif)](https://www.github.com/florent37/AwesomeBar)

# Usage

```xml
<com.github.florent37.awesomebar.AwesomeBar
            android:id="@+id/bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@android:color/white"
            app:bar_primaryColor="#000000"
            app:bar_primaryColorDark="#000000"
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

bar.displayHomeAsUpEnabled(true / false);
```

Add an overflow

```java
bar.addOverflowItem("overflow 1");
bar.addOverflowItem("overflow 2");

bar.setOverflowActionItemClickListener(new AwesomeBar.OverflowActionItemClickListener() {
    @Override
    public void onOverflowActionItemClicked(int position, String item) {

    }
});
```

# Download

<a href='https://ko-fi.com/A160LCC' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

In your module [![Download](https://api.bintray.com/packages/florent37/maven/AwesomeBar/images/download.svg)](https://bintray.com/florent37/maven/AwesomeBar/_latestVersion)
```groovy
compile 'com.github.florent37:awesomebar:1.0.3'
```

# Inspiration

Gmail Mobile from **Weekz**

[https://material.uplabs.com/posts/gmail-mobile-concept](https://material.uplabs.com/posts/gmail-mobile-concept)

# Changelog

## 1.0.3

Can change backgrond color

## 1.0.2

Added back button

## 1.0.1

Added overflow menu

# Credits

Author: Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/)

Fiches Plateau Moto : [https://www.fiches-plateau-moto.fr/](https://www.fiches-plateau-moto.fr/)

<a href="https://goo.gl/WXW8Dc">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

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
