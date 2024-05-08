# Smart Filter Library

This is a library for creating and managing radio groups in Android. It provides support for single line, multi line and row item radio groups.

## Features

- **Single Line Radio Group**: A radio group that displays its items in a single line.
- **Multi Line Radio Group**: A radio group that displays its items in multiple lines.
- **Row Item Radio Group**: A radio group that displays its items in a row.

## Getting Started

- **These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

    ### Prerequisites

  - ** Android Studio Iguana | 2023.2.1 Patch 2 or later
  - ** JDK 8
  - ** Android SDK 24 or later

    ### Installing

  - ** Clone the repository: `git clone https://github.com/smartSenseSolutions/smart-filter-library.git`
  - ** Open the project in Android Studio.
  - ** Build and run the project on an emulator or device.

    ### Usage

  - **To use the Smart Filter library in your Android application, you need to include it in your project's dependencies. Add the following line to your `build.gradle` file:

    ### Events Handling
  - **BaseEventListener** is an interface that provides a set of methods to handle events in the Smart Filter library. You can implement this interface in your activity or fragment to handle these events. The following events are available:

  - **onSingleSelectionChanged**: This method is called when the selected item in a single selection radio group is changed.
  - **onMultiSelectionChanged**: This method is called when the selected item in a multi selection radio group is changed.
  - **onChipSelected**: This method is called when a chip is selected in a chip group.
  - **onChipUnselected**: This method is called when a chip is unselected in a chip group.


## SingleSelection

  - **SingleSelectionRadioGroup(Verticle/Horizontal)
    
    - ***Verticle
                   
          SmartFilter.addRadioGroupSingleSelection {
                addRadioGroupSingleLineVertical(binding.root) { radioGroupData ->
                      toast("name: ${radioGroupData.name} ") }}
       
      
   - ***Horizontal
      
            SmartFilter.addRadioGroupSingleSelection {
                  addRadioGroupSingleLineHorizontal(binding.root) { radioGroupData ->
                        toast("name: ${radioGroupData.name} ") } }
    
         
    
   <img src="media/single_selection_vertical.png" width="250" />                      <img src="media/single_selection_horizontal.png" width="250" />


  - **SingleSelectionMultiLine(MultiRaw)
       
           SmartFilter.addRadioMultiRawSingleSelection {
                addRadioMultiRow(binding.root) { radioGroupData ->
                    toast("name: ${radioGroupData.name} ")  } }

    <img src="media/single_selection_multiline.png" width="250" />

  - **SingleSelectionRowItem(Verticle/Horizontal)

    - ***Verticle
        
            SmartFilter.addRadioRawItemSingleSelection {
                  addRadioGroupRowItemVertical(binding.root) { radioGroupData ->
                      showMessage(radioGroupData) } }

      - ***Horizontal
  
            SmartFilter.addRadioRawItemSingleSelection {
                  addRadioGroupSingleLineHorizontal(binding.root) { radioGroupData ->
                      toast("name: ${radioGroupData.name} ")  } }

        <img src="media/single_selection_row_item_horizontal.png" width="250" /> <img src="media/single_selection_row_item_verticle.png.png" width="250" />

        - ** SingleSelectionChipGroup((Verticle/Horizontal))

              SmartFilter.addChipGroupSingleSelection {
                  addSingleSelectionChipGroup(binding.root) { radioGroupData ->
                      toast("name: ${radioGroupData.name} ") } }
        
           <img src="media/single_selection_chip.png" width="250" />       
   
          - ** MultiSelectionChipGroup(MultiRaw)
        
                SmartFilter.addChipGroupMultiSelection {
                    addMultiSelectionChipGroup(binding.root) { radioGroupData ->
                         toast("Checked IDs: ${radioGroupData.joinToString(", ")}")
                      }
                  }
         
             
   <img src="media/multiselect_chip.png" width="250" />

### Attribute      
         
    | Attribute | Description | Type | Default Value |
    | --- | --- | --- | --- |
    | rootView | The root view of the radio group. | ViewGroup | - |
    | singleGroupSubType | The type of the radio group. | SingleGroupSubType | - |
    | orientation | The orientation of the radio group. | Orientation | VERTICAL |
    | mData | The list of radio group items. | List<RadioGroupData> | - |
    | callbacks | The event listener for the radio group. | BaseEventListener | - |
    | bgSelector | The background selector for the radio group items. | Int | - |
    | textSelector | The text color selector for the radio group items. | Int | - |
     
 
### Dependency
```kotlin
implementation 'com.ss:smartfilterlib:1.0.0'