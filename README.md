# Smart Filter Library

This library provides a set of customizable filter components for Android applications. It supports both single and multi-selection filters in various formats such as radio groups, chip groups, and list views. The filters can be displayed either vertically or horizontally.

## Features

- Single Line Radio Group (Vertical/Horizontal)
- Multi Line Radio Group
- Row Item Radio Group (Vertical/Horizontal)
- Range Selection
- Chip Group Single Selection (Vertical/Horizontal)
- Chip Group Multi Selection (Vertical/Horizontal)
- Checked Single Selection (Vertical/Horizontal)
- Checked Multi Selection (Vertical/Horizontal)

## Getting Started

These instructions will guide you on how to get a copy of the project up and running on your local machine.

## Prerequisites

Before you can run the project, you need to have Android Studio installed on your machine. You also need to have a suitable Android device or emulator for running the application.

## Installing

1. Clone the repository: `git clone https://github.com/smartSenseSolutions/smart-filter-library.git`
2. Open the project in Android Studio.
3. Build and run the project on an emulator or device.

## Usage

The library provides a set of classes and methods for creating and managing filter components. Each filter type has its own class and configuration methods. The filters can be added to any ViewGroup in your layout.

The library also provides a set of default styles for the filters. You can customize these styles by providing your own drawable and color resources.

## Dependencies

The library depends on the following libraries:

- `androidx.appcompat:appcompat:1.3.1`
- `androidx.core:core-ktx:1.6.0`


## SingleSelection

### SingleSelectionRadioGroup(Verticle/Horizontal)

- **Verticle**


          SmartFilter.addRadioGroupSingleSelection {
                addRadioGroupSingleLineVertical(binding.root) { radioGroupData ->
                      toast("name: ${radioGroupData.name} ") }}



- **Horizontal**


            SmartFilter.addRadioGroupSingleSelection {
                  addRadioGroupSingleLineHorizontal(binding.root) { radioGroupData ->			
                        toast("name: ${radioGroupData.name} ") } }



### SingleSelectionMultiLine(MultiRaw)

           SmartFilter.addRadioMultiRawSingleSelection {
                addRadioMultiRow(binding.root) { radioGroupData ->
                    toast("name: ${radioGroupData.name} ")  } }




### SingleSelectionRowItem(Verticle/Horizontal)

- **Verticle**

         SmartFilter.addRadioRawItemSingleSelection {
            addRadioGroupRowItemVertical(binding.root) { radioGroupData ->
                toast("name: ${radioGroupData.name} ")  } }



- **Horizontal**

       SmartFilter.addRadioRawItemSingleSelection {
         addRadioGroupRowItemHorizontal(binding.root) { radioGroupData ->
             toast("name: ${radioGroupData.name} ")  } }




### SingleSelectionChipGroup((Verticle/Horizontal))

               SmartFilter.addChipGroupSingleSelection {
                  addSingleSelectionChipGroupVertical(binding.root) { radioGroupData ->
                      toast("name: ${radioGroupData.name} ") } }

- **Horizontal**
 
              SmartFilter.addChipGroupSingleSelection {
                    addSingleSelectionChipGroupHorizontal(binding.root) { radioGroupData ->
                        toast("name: ${radioGroupData.name} ") } }
                 
   

### MultiSelectionChipGroup(MultiRaw - (Verticle/Horizontal))

- **Verticle**
              
              SmartFilter.addChipGroupMultiSelection {
                    addMultiSelectionChipGroupVertical(binding.root) { radioGroupData ->
                       toast("Checked IDs: ${radioGroupData.joinToString(", ")}")  } }
- **Horizontal**

                 SmartFilter.addChipGroupMultiSelection {
                    addMultiSelectionChipGroupHorizontal(binding.root) { radioGroupData ->
                        toast("Checked IDs: ${radioGroupData.joinToString(", ")}")}}
        



### SingleSelectionListView((Verticle/Horizontal))

- **Verticle**
              
             SmartFilter.addListViewSingleSelection {
                addSingleSelectionListVertical(binding.root) { radioGroupData ->
                toast("name:  " + radioGroupData.name)  } }

- **Horizontal**

                SmartFilter.addListViewSingleSelection {
                   addSingleSelectionListHorizontal(binding.root) { radioGroupData ->
                        toast("name:  " + radioGroupData.name)  } }




### MultiSelectionListView((Verticle/Horizontal))

- **Verticle**

             SmartFilter.addListViewMultiSelection{
                  addMultiSelectionListVertical(binding.root) { radioGroupData ->
                      toast("name:  " + radioGroupData)  } }


  
  - **Horizontal**

          SmartFilter.addListViewMultiSelection{
             addMultiSelectionListHorizontal(binding.root) { radioGroupData ->
                    toast("name:  " + radioGroupData)  } }



<img src="media/test1.png" width="250" />   <img src="media/test2.png" width="250" />  
<img src="media/test3.png" width="250" />   <img src="media/test4.png" width="250" />   

https://raw.githubusercontent.com/MalaRuparel2023/smartSenseSolutions/smartFilter/main/media/test.mp4


### Dependency
```kotlin
implementation 'com.ss:smartfilterlib:1.0.0'
