# Smart Filter Library

This is a library for creating and managing radio groups in Android. It provides support for single line, multi line and row item radio groups.

## Features

- Single Line Radio Group: A radio group that displays its items in a single line.
- Multi Line Radio Group: A radio group that displays its items in multiple lines.
- Row Item Radio Group: A radio group that displays its items in a row.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Android Studio Iguana | 2023.2.1 Patch 2 or later
- JDK 8
- Android SDK 24 or later

### Installing

1. Clone the repository: `git clone https://github.com/smartSenseSolutions/smart-filter-library.git`
2. Open the project in Android Studio.
3. Build and run the project on an emulator or device.

## Usage

To use the Smart Filter library in your Android application, you need to include it in your project's dependencies. Add the following line to your `build.gradle` file:

### Default Drawable for SingleSelectionRadioGroup

        @DrawableRes val bgSelector: Int = androidx.appcompat.R.drawable.abc_btn_radio_material,
        @ColorRes val textSelector: Int = android.R.color.black

##Selector

         bgSelector = R.drawable.singleline_rb_selector,
        textSelector = R.color.single_text_color_selector

###  Drawable for SingleSelectionMultiRaw

##Default
       
         ```kotlin
        @DrawableRes val bgSelector: Int = R.drawable.multiline_default,
        @ColorRes val textSelector: Int = android.R.color.black

##Selector  

            ```kotlin    
            bgSelector = R.drawable.multiline_selector,
            textSelector = R.color.multiline_text_selector


```kotlin
implementation 'com.ss:smartfilterlib:1.0.0'
