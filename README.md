# RoundCornerImageView

A RoundCornerImageView like this:

![rcimageview](./image/rcimageview.png)

# Installation

gradle:

```
compile 'com.liuzhuang.opensource:rcimageview:0.0.1'
```

or maven:

```
<dependency>
  <groupId>com.liuzhuang.opensource</groupId>
  <artifactId>rcimageview</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

# Usage

```
<com.android.liuzhuang.rcimageview.RoundCornerImageView
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:src="@mipmap/demo"
    android:scaleType="centerCrop"
    rc:rciv_radius="10dp"/>
```

# Advantages

1. Does not copy bitmap.
2. Only draw once.
3. Supports alpha, antialisasing and hardware acceleration.