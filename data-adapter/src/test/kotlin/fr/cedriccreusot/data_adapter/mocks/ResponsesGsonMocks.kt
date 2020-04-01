package fr.cedriccreusot.data_adapter.mocks

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.cedriccreusot.data_adapter.retrofit.model.Photo
import fr.cedriccreusot.data_adapter.retrofit.model.Statistics

object ResponsesGsonMocks {

    fun createListPhotos(): List<Photo> {
        val listType = object : TypeToken<List<Photo>>() {
        }.type
        return GsonBuilder().create().fromJson(
            """
                        [
                          {
                            "id": "LBI7cgq3pbM",
                            "created_at": "2016-05-03T11:00:28-04:00",
                            "updated_at": "2016-07-10T11:00:01-05:00",
                            "width": 5245,
                            "height": 3497,
                            "color": "#60544D",
                            "likes": 12,
                            "liked_by_user": false,
                            "description": "A man drinking a coffee.",
                            "user": {
                              "id": "pXhwzz1JtQU",
                              "username": "poorkane",
                              "name": "Gilbert Kane",
                              "portfolio_url": "https://theylooklikeeggsorsomething.com/",
                              "bio": "XO",
                              "location": "Way out there",
                              "total_likes": 5,
                              "total_photos": 74,
                              "total_collections": 52,
                              "instagram_username": "instantgrammer",
                              "twitter_username": "crew",
                              "profile_image": {
                                "small": "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32",
                                "medium": "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64",
                                "large": "https://images.unsplash.com/face-springmorning.jpg?q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"
                              },
                              "links": {
                                "self": "https://api.unsplash.com/users/poorkane",
                                "html": "https://unsplash.com/poorkane",
                                "photos": "https://api.unsplash.com/users/poorkane/photos",
                                "likes": "https://api.unsplash.com/users/poorkane/likes",
                                "portfolio": "https://api.unsplash.com/users/poorkane/portfolio"
                              }
                            },
                            "current_user_collections": [
                              {
                                "id": 206,
                                "title": "Makers: Cat and Ben",
                                "published_at": "2016-01-12T18:16:09-05:00",
                                "updated_at": "2016-07-10T11:00:01-05:00",
                                "cover_photo": null,
                                "user": null
                              }
                            ],
                            "urls": {
                              "raw": "https://images.unsplash.com/face-springmorning.jpg",
                              "full": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg",
                              "regular": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=1080&fit=max",
                              "small": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=400&fit=max",
                              "thumb": "https://images.unsplash.com/face-springmorning.jpg?q=75&fm=jpg&w=200&fit=max"
                            },
                            "links": {
                              "self": "https://api.unsplash.com/photos/LBI7cgq3pbM",
                              "html": "https://unsplash.com/photos/LBI7cgq3pbM",
                              "download": "https://unsplash.com/photos/LBI7cgq3pbM/download",
                              "download_location": "https://api.unsplash.com/photos/LBI7cgq3pbM/download"
                            }
                          }
                        ]
                    """.trimIndent(), listType
        )
    }

    fun createStatistics(): Statistics {
        return GsonBuilder().create().fromJson("""
            {
                "id": "LF8gK8-HGSg",
                "downloads": {
                    "total": 49771,
                    "historical": {
                        "change": 1474,
                        "resolution": "days",
                        "quantity": 30,
                        "values": [
                          { "date": "2017-02-07", "value": 6 },
                          { "date": "2017-02-08", "value": 102 },
                          { "date": "2017-02-09", "value": 82 },
                          { "date": "2017-02-10", "value": 63 },
                          { "date": "2017-02-11", "value": 37 },
                          { "date": "2017-02-12", "value": 33 },
                          { "date": "2017-02-13", "value": 62 },
                          { "date": "2017-02-14", "value": 59 },
                          { "date": "2017-02-15", "value": 64 },
                          { "date": "2017-02-16", "value": 46 },
                          { "date": "2017-02-17", "value": 49 },
                          { "date": "2017-02-18", "value": 21 },
                          { "date": "2017-02-19", "value": 32 },
                          { "date": "2017-02-20", "value": 55 },
                          { "date": "2017-02-21", "value": 53 },
                          { "date": "2017-02-22", "value": 48 },
                          { "date": "2017-02-23", "value": 59 },
                          { "date": "2017-02-24", "value": 60 },
                          { "date": "2017-02-25", "value": 21 },
                          { "date": "2017-02-26", "value": 14 },
                          { "date": "2017-02-27", "value": 44 },
                          { "date": "2017-02-28", "value": 58 },
                          { "date": "2017-03-01", "value": 47 },
                          { "date": "2017-03-02", "value": 60 },
                          { "date": "2017-03-03", "value": 42 },
                          { "date": "2017-03-04", "value": 23 },
                          { "date": "2017-03-05", "value": 24 },
                          { "date": "2017-03-06", "value": 55 },
                          { "date": "2017-03-07", "value": 64 },
                          { "date": "2017-03-08", "value": 37 }
                        ]
                    }
                },
                "views": {
                    "total": 5165988,
                    "historical": {
                        "change": 165009,
                        "resolution": "days",
                        "quantity": 30,
                        "values": [
                          { "date": "2017-02-07", "value": 8422 },
                          { "date": "2017-02-08", "value": 8770 },
                          { "date": "2017-02-09", "value": 8625 },
                          { "date": "2017-02-10", "value": 7534 },
                          { "date": "2017-02-11", "value": 3812 },
                          { "date": "2017-02-12", "value": 4565 },
                          { "date": "2017-02-13", "value": 8435 },
                          { "date": "2017-02-14", "value": 8054 },
                          { "date": "2017-02-15", "value": 7884 },
                          { "date": "2017-02-16", "value": 5054 },
                          { "date": "2017-02-17", "value": 7518 },
                          { "date": "2017-02-18", "value": 3848 },
                          { "date": "2017-02-19", "value": 4531 },
                          { "date": "2017-02-20", "value": 7990 },
                          { "date": "2017-02-21", "value": 9852 },
                          { "date": "2017-02-22", "value": 7679 },
                          { "date": "2017-02-23", "value": 7664 },
                          { "date": "2017-02-24", "value": 6482 },
                          { "date": "2017-02-25", "value": 3692 },
                          { "date": "2017-02-26", "value": 3908 },
                          { "date": "2017-02-27", "value": 9779 },
                          { "date": "2017-02-28", "value": 11230 },
                          { "date": "2017-03-01", "value": 7243 },
                          { "date": "2017-03-02", "value": 7857 },
                          { "date": "2017-03-03", "value": 7521 },
                          { "date": "2017-03-04", "value": 3779 },
                          { "date": "2017-03-05", "value": 4452 },
                          { "date": "2017-03-06", "value": 7885 },
                          { "date": "2017-03-07", "value": 7649 },
                          { "date": "2017-03-08", "value": 7227 }
                        ]
                    }
                },
                "likes": {
                    "total": 263,
                    "historical": {
                        "change": 19,
                        "resolution": "days",
                        "quantity": 30,
                        "values": [
                          { "date": "2017-02-07", "value": 2 },
                          { "date": "2017-02-08", "value": 0 },
                          { "date": "2017-02-09", "value": 2 },
                          { "date": "2017-02-10", "value": 0 },
                          { "date": "2017-02-11", "value": 0 },
                          { "date": "2017-02-12", "value": 0 },
                          { "date": "2017-02-13", "value": 0 },
                          { "date": "2017-02-14", "value": 1 },
                          { "date": "2017-02-15", "value": 3 },
                          { "date": "2017-02-16", "value": 0 },
                          { "date": "2017-02-17", "value": 1 },
                          { "date": "2017-02-18", "value": 0 },
                          { "date": "2017-02-19", "value": 1 },
                          { "date": "2017-02-20", "value": 1 },
                          { "date": "2017-02-21", "value": 0 },
                          { "date": "2017-02-22", "value": 0 },
                          { "date": "2017-02-23", "value": 0 },
                          { "date": "2017-02-24", "value": 0 },
                          { "date": "2017-02-25", "value": 0 },
                          { "date": "2017-02-26", "value": 2 },
                          { "date": "2017-02-27", "value": 0 },
                          { "date": "2017-02-28", "value": 1 },
                          { "date": "2017-03-01", "value": 1 },
                          { "date": "2017-03-02", "value": 1 },
                          { "date": "2017-03-03", "value": 1 },
                          { "date": "2017-03-04", "value": 0 },
                          { "date": "2017-03-05", "value": 0 },
                          { "date": "2017-03-06", "value": 1 },
                          { "date": "2017-03-07", "value": 0 },
                          { "date": "2017-03-08", "value": 1 }
                        ]
                    }
                }
            }
        """.trimIndent(), Statistics::class.java)
    }
}