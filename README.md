# Smart Filter Library

This is a library for creating and managing radio groups in Android. It provides support for single line, multi line and row item radio groups.

## Features

- **Single Line Radio Group**: A radio group that displays its items in a single line.
- **Multi Line Radio Group**: A radio group that displays its items in multiple lines.
- **Row Item Radio Group**: A radio group that displays its items in a row.

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

## Events Handling
**BaseEventListener** is an interface that provides a set of methods to handle events in the Smart Filter library. You can implement this interface in your activity or fragment to handle these events. The following events are available:

- **onSingleSelectionChanged**: This method is called when the selected item in a single selection radio group is changed.
- **onMultiSelectionChanged**: This method is called when the selected item in a multi selection radio group is changed.
- **onChipSelected**: This method is called when a chip is selected in a chip group.
- **onChipUnselected**: This method is called when a chip is unselected in a chip group.

## Different methods to call from MainActivity to call single and multi line radio group ,chip group and row item radio group
## SingleSelection
### SingleSelectionRadioGroup(Verticle/Horizontal)
  
    ### How to Use
    ```kotlin
     SmartFilter.addSingleSelectionRadioGroup(addSingleSelectionRadioGroup(binding.root, this))
    ```
       ```
    ### Params
    ```kotlin

    fun SingleSelectionRadioData(rootView: ViewGroup, callback: BaseEventListener) =
        SingleSelectionParams(
        rootView = rootView,
        singleGroupSubType = SingleGroupSubType.SINGLE_LINE,
        orientation = Orientation.VERTICAL,
        mData = mRadioGroupData(),
        callbacks = callback,
        bgSelector = R.drawable.singleline_selector,
        textSelector = R.color.single_text_color_selector
    )
### Verticle
    orientation = Orientation.VERTICAL,
    src/main/res/assets/single_selection_verticle.png
    ```
### Horizontal
    src/main/res/assets/single_selection_horizental.png
    orientation = Orientation.HORIZONTAL,

### SingleSelectionMultiLine(MultiRaw)
    ```kotlin
    SmartFilter.addSingleSelectionRadioGroup(singleSelectionMultiLine(binding.root, this))
    ```
    ### Params
    ```kotlin

    fun singleSelectionMultiLine(rootView: ViewGroup, callback: BaseEventListener) =
    SingleSelectionParams(
    rootView = rootView,
    singleGroupSubType = SingleGroupSubType.MULTI_LINE,
    mData = mRadioGroupData(),
    callbacks = callback,
    bgSelector = R.drawable.multiline_bg_selector,
    textSelector = R.color.multiline_text_selector
    )   




      
         
    
    | Attribute | Description | Type | Default Value |
    | --- | --- | --- | --- |
    | rootView | The root view of the radio group. | ViewGroup | - |
    | singleGroupSubType | The type of the radio group. | SingleGroupSubType | - |
    | orientation | The orientation of the radio group. | Orientation | VERTICAL |
    | mData | The list of radio group items. | List<RadioGroupData> | - |
    | callbacks | The event listener for the radio group. | BaseEventListener | - |
    | bgSelector | The background selector for the radio group items. | Int | - |
    | textSelector | The text color selector for the radio group items. | Int | - |
     
 

```kotlin
implementation 'com.ss:smartfilterlib:1.0.0'
