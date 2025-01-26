package ro.cosminmihu.ktor.monitor.library.domain.model

enum class ContentType(val contentType: String, val contentName: String, val color: String) {
    TEXT_PLAIN("text/plain", "TXT", "#FFA07A"),
    TEXT_HTML("text/html", "HTML", "#D3D3D3"),
    TEXT_CSS("text/css", "CSS", "#90EE90"),
    TEXT_JAVASCRIPT("text/javascript", "JS", "#ADD8E6"),
    TEXT_XML("text/xml", "XML", "#FFD700"),
    APPLICATION_JSON("application/json", "JSON", "#FF69B4"),
    APPLICATION_XML("application/xml", "XML", "#FFD700"),
    APPLICATION_JAVASCRIPT("application/javascript", "JS", "#ADD8E6"),
    APPLICATION_PDF("application/pdf", "PDF", "#FF6347"),
    APPLICATION_ZIP("application/zip", "ZIP", "#708090"),
    APPLICATION_GZIP("application/gzip", "GZ", "#778899"),
    APPLICATION_WWW_FORM_URLENCODED("application/x-www-form-urlencoded", "FORM", "#DA70D6"),
    APPLICATION_VND_API_JSON("application/vnd.api+json", "API", "#FF1493"),
    APPLICATION_OCTET_STREAM("application/octet-stream", "BIN", "#A0522D"),
    IMAGE_JPEG("image/jpeg", "JPEG", "#FFB6C1"),
    IMAGE_PNG("image/png", "PNG", "#98FB98"),
    IMAGE_GIF("image/gif", "GIF", "#FFD700"),
    IMAGE_WEBP("image/webp", "WEBP", "#20B2AA"),
    IMAGE_SVG_XML("image/svg+xml", "SVG", "#DA70D6"),
    IMAGE_X_ICON("image/x-icon", "ICO", "#FF8C00"),
    AUDIO_MPEG("audio/mpeg", "MP3", "#87CEFA"),
    AUDIO_OGG("audio/ogg", "OGG", "#B0E0E6"),
    AUDIO_WAV("audio/wav", "WAV", "#4682B4"),
    AUDIO_WEBM("audio/webm", "WEBM", "#00CED1"),
    VIDEO_MP4("video/mp4", "MP4", "#FFDAB9"),
    VIDEO_MPEG("video/mpeg", "MPEG", "#DAA520"),
    VIDEO_OGG("video/ogg", "OGV", "#F0E68C"),
    VIDEO_WEBM("video/webm", "WEBM", "#ADFF2F"),
    MULTIPART_FORM_DATA("multipart/form-data", "FORM", "#FF4500"),
    MULTIPART_BYTERANGES("multipart/byteranges", "BYTES", "#DAA520"),
    MULTIPART_MIXED("multipart/mixed", "MIX", "#FF69B4"),
    UNKNOWN("", "?", "#808080");
}

val String.contentType
    get() = ContentType.entries
        .firstOrNull { this.contains(it.contentType, true) }
        ?: ContentType.UNKNOWN

val ContentType.asColor
    get() = color.toColor()
