# Pigeon
Pigeon - a news app in android. This android application fetches news using retrofit from NewsAPI.

<img src="https://github.com/PriyabrataNaskar/Pigeon/blob/master/app/src/main/res/raw/pigeon_splash_image.gif" alt="Pigeon Logo" width="300"/>

## In Pigeon you can find news in the following categories: 

 - Suggested
 - Business
 - Technology
 - General
 - Entertainmemnt
 - Health
 - Science
 - Sports
 
 
![Splash Screen](https://github.com/PriyabrataNaskar/Pigeon/blob/master/screenshot/Splash%20Screen.png)

![HOME PAGE](https://github.com/PriyabrataNaskar/Pigeon/blob/master/screenshot/Home%20Page%20(Horizontal).png)

![News Page](https://github.com/PriyabrataNaskar/Pigeon/blob/master/screenshot/News%20Detail%20Page%20(Horizontal)%20(1).png)

### You can view the design in [Figma Prototype](https://www.figma.com/proto/s0UUXrsQc1PdUmgHdAdGTR/News-App?node-id=0%3A1&scaling=scale-down&page-id=0%3A1&starting-point-node-id=84%3A7)

All the news are fetched from the news api. If you're cloning this project in your own machine don't forget to modify the ```API KEY```. You will get a new API Key on the [NEWS API](https://newsapi.org) website

All the response you will get ion JSON format.

### Example response
```
{
  "status": "ok",
    "sources": [
                  {
                    "id": "australian-financial-review",
                    "name": "Australian Financial Review",
                    "description": "The Australian Financial Review reports the latest news from business, finance, investment and politics, updated in real time. It has a reputation for independent, award-winning journalism and is essential reading for the business and investor community.",
                     "url": "http://www.afr.com",
                     "category": "business",
                     "language": "en",
                     "country": "au"
                    },
                    {
                      "id": "bloomberg",
                      "name": "Bloomberg",
                      "description": "Bloomberg delivers business and markets news, data, analysis, and video to the world, featuring stories from Businessweek and Bloomberg News.",
                      "url": "http://www.bloomberg.com",
                      "category": "business",
                      "language": "en",
                      "country": "us"
                      },
                      {
                      "id": "business-insider",
                      "name": "Business Insider",
                      "description": "Business Insider is a fast-growing business site with deep financial, media, tech, and other industry verticals. Launched in 2007, the site is now the largest business news site on the web.",
                      "url": "http://www.businessinsider.com",
                      "category": "business",
                      "language": "en",
                      "country": "us"
                      }
                ]
}


```

### I'm using 

- [Retrofit](https://square.github.io/retrofit/) for network calls in background thread
- [Glide](https://github.com/bumptech/glide) to load images over the internet.
- [GSON Library](https://github.com/google/gson) to parse the JSON
- [Lottie](https://github.com/airbnb/lottie-android) to play the animation

## Acknowledgements

Thanks to these projects and libraries:

## **Libraries**

- [Retrofit](https://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide)
- [GSON Library](https://github.com/google/gson)
- [MDC](https://material.io/develop/android/docs/getting-started)
- [Lottie](https://github.com/airbnb/lottie-android)
