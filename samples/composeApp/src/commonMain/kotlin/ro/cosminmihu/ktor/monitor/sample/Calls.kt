package ro.cosminmihu.ktor.monitor.sample

import io.ktor.client.request.get

internal suspend fun makeCalls() {
//        delay(2.seconds)
    listOf(
//            "https://png.pngtree.com/png-vector/20250117/ourlarge/pngtree-goldfish-ornamental-fish-creature-orange-cute-cartoon-three-dimensional-png-image_15181370.png",
//            "https://gsp.ro/", // TODO redirect
//            "https://media.istockphoto.com/id/1973365581/vector/sample-ink-rubber-stamp.jpg?s=612x612&w=0&k=20&c=_m6hNbFtLdulg3LK5LRjJiH6boCb_gcxPvRLytIz0Ws=",
        "https://dexonline.ro/cuvantul-zilei/json",
//            "hts://plm.xss/",
        "https://dexonline.ro/cuvantul-zilei/xml",
        "https://github.com/",
        "https://github.githubassets.com/assets/light-7aa84bb7e11e.css",
//            "ws://ws.mock",
//            "https://mp5a3efdb353ef904741.free.beeceptor.com/data"
    ).map {
        runCatching {
            httpClient().get(it)
        }
    }

//        runCatching {
//            delay(15.seconds)
//            httpClient().submitFormWithBinaryData(
//                url = "https://tests.free.beeceptor.com/get-api-token",
//                formData = formData {
//                    append("description", "Ktor logo")
//                }
//            )
//        }
}
