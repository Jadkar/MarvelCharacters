package com.openbank.domain.model


object DetailCharacterConverter {

    fun convertComicsItem(comicsItem: ComicsModel): List<ItemModel> {

        val convertedItemList = ArrayList<ItemModel>()
        for (item in comicsItem.items ?: emptyList()) {
            val listItem = ItemModel()
            listItem.name = item.name
            listItem.resourceURI = item.resourceURI
            convertedItemList.add(listItem)
        }

        return convertedItemList
    }

    fun convertSeriesItem(seriesItem: SeriesModel): List<ItemModel> {
        val convertedItemList = ArrayList<ItemModel>()
        for (item in seriesItem.items ?: emptyList()) {
            val listItem = ItemModel()
            listItem.name = item.name
            listItem.resourceURI = item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

    fun convertStoriesItem(storiesItem: StoriesModel): List<ItemModel> {
        val convertedItemList = ArrayList<ItemModel>()
        for (item in storiesItem.storiesItems ?: emptyList()) {

            val listItem = ItemModel()
            listItem.name = item.name
            listItem.resourceURI = item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

    fun convertEventsItem(eventsItem: EventsModel): List<ItemModel> {
        val convertedItemList = ArrayList<ItemModel>()
        for (item in eventsItem.items ?: emptyList()) {

            val listItem = ItemModel()
            listItem.name = item.name
            listItem.resourceURI = item.resourceURI
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

    fun convertUrlsItem(urlsItem: List<UrlModel>): List<ItemModel> {
        val convertedItemList = ArrayList<ItemModel>()
        for (item in urlsItem) {

            val listItem = ItemModel()
            listItem.name = item.type
            listItem.resourceURI = item.url
            convertedItemList.add(listItem)
        }
        return convertedItemList
    }

}