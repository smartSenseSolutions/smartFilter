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

<img src="media/single_selection_vertical.png" width="250" />


- **Horizontal**


            SmartFilter.addRadioGroupSingleSelection {
                  addRadioGroupSingleLineHorizontal(binding.root) { radioGroupData ->			
                        toast("name: ${radioGroupData.name} ") } }


<img src="media/single_selection_horizontal.png" width="250" />


### SingleSelectionMultiLine(MultiRaw)

           SmartFilter.addRadioMultiRawSingleSelection {
                addRadioMultiRow(binding.root) { radioGroupData ->
                    toast("name: ${radioGroupData.name} ")  } }


<img src="media/single_selection_multiline.png" width="250" />


### SingleSelectionRowItem(Verticle/Horizontal)

- **Verticle**

         SmartFilter.addRadioRawItemSingleSelection {
            addRadioGroupRowItemVertical(binding.root) { radioGroupData ->
                toast("name: ${radioGroupData.name} ")  } }


   <img src="media/single_selection_row_item_vertical.png" width="250" />

- **Horizontal**

       SmartFilter.addRadioRawItemSingleSelection {
         addRadioGroupRowItemHorizontal(binding.root) { radioGroupData ->
             toast("name: ${radioGroupData.name} ")  } }


  <img src="media/single_selection_row_item_horizontal.png" width="250" /> 

### SingleSelectionChipGroup((Verticle/Horizontal))

               SmartFilter.addChipGroupSingleSelection {
                  addSingleSelectionChipGroupVertical(binding.root) { radioGroupData ->
                      toast("name: ${radioGroupData.name} ") } }

- **Horizontal**
 
              SmartFilter.addChipGroupSingleSelection {
                    addSingleSelectionChipGroupHorizontal(binding.root) { radioGroupData ->
                        toast("name: ${radioGroupData.name} ") } }
                 
  <img src="media/single_selection_chip.png" width="250" />       

### MultiSelectionChipGroup(MultiRaw - (Verticle/Horizontal))

- **Verticle**
              
              SmartFilter.addChipGroupMultiSelection {
                    addMultiSelectionChipGroupVertical(binding.root) { radioGroupData ->
                       toast("Checked IDs: ${radioGroupData.joinToString(", ")}")  } }
- **Horizontal**

                 SmartFilter.addChipGroupMultiSelection {
                    addMultiSelectionChipGroupHorizontal(binding.root) { radioGroupData ->
                        toast("Checked IDs: ${radioGroupData.joinToString(", ")}")}}
        
   <img src="media/multiselect_chip.png" width="250" />


### SingleSelectionListView((Verticle/Horizontal))

- **Verticle**
              
             SmartFilter.addListViewSingleSelection {
                addSingleSelectionListVertical(binding.root) { radioGroupData ->
                toast("name:  " + radioGroupData.name)  } }

    <img src="media/single_selection_vertical.png.png" width="250" />   
- **Horizontal**

                SmartFilter.addListViewSingleSelection {
                   addSingleSelectionListHorizontal(binding.root) { radioGroupData ->
                        toast("name:  " + radioGroupData.name)  } }

<img src="media/single_selection_horizental.png.png.png" width="250" />   


### MultiSelectionListView((Verticle/Horizontal))

- **Verticle**

             SmartFilter.addListViewMultiSelection{
                  addMultiSelectionListVertical(binding.root) { radioGroupData ->
                      toast("name:  " + radioGroupData)  } }

    <img src="media/multi_selection_vertical.png" width="250" />   
  
  - **Horizontal**

          SmartFilter.addListViewMultiSelection{
             addMultiSelectionListHorizontal(binding.root) { radioGroupData ->
                    toast("name:  " + radioGroupData)  } }

  <img src="media/multi_selection_horizontal.png" width="250" />   



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


### Dependency
```kotlin
implementation 'com.ss:smartfilterlib:1.0.0'
