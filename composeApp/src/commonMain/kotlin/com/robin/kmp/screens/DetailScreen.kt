package com.robin.kmp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.robin.kmp.customIcons.CustomIcon
import com.robin.kmp.model.Surah

@Composable
fun DetailScreen(name: String, server: String, surahs: String, navigateUp: () -> Unit) {
    val allSurahList = mutableListOf<Surah>()
    LaunchedEffect(Unit) {
        allSurahList.clear()
        allSurahList.addAll(surahList(surahs))
    }
    if (allSurahList.size < 1) {
        allSurahList.addAll(surahList(surahs))
    }
    Scaffold(topBar = {
        TopAppBar(
            elevation = 8.dp,
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xffCD0000), Color(0xffE70000), Color(0xffCD0000))
                )
            ),
            backgroundColor = Color(0xffCD0000),
            title = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name, fontSize = 18.sp, color = Color.White)
                }
            },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Go Back"
                    )
                }
            }
        )
    }) {

        Box(
            modifier = Modifier.fillMaxSize().background(color = Color(0xffFFFFFF)).padding(0.dp),
            contentAlignment = Alignment.Center
        ) {
            if (allSurahList != null && allSurahList.size > 0) {
                Column(
                    modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())
                ) {
                    allSurahList.forEach {
                        Row(
                            Modifier.fillMaxWidth().padding(10.dp).clickable {
                                //   onItemClick(it)
                                // println("Clicked")
                                var mp3Url = ""
                                /* if(it.serialNo<10){
                                     mp3Url = server+"/00"+it.serialNo+".mp3"
                                 }*/
                                when (it.serialNo) {

                                    in 1..9 -> mp3Url = server + "/00" + it.serialNo + ".mp3"
                                    in 10..99 -> mp3Url = server + "/0" + it.serialNo + ".mp3"
                                    in 100..114 -> mp3Url = server + "/" + it.serialNo + ".mp3"
                                }
                                println("Mp3 Url: $mp3Url")
                                // open link in browser



                            },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(modifier = Modifier.shadow(4.dp, shape = CircleShape)) {
                                CustomIcon(text = (allSurahList.indexOf(it) + 1).toString())
                            }
                            Spacer(modifier = Modifier.width(0.dp))
                            Row(
                                modifier = Modifier.weight(1f).padding(start = 10.dp, end = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(horizontalAlignment = Alignment.Start) {
                                    Text(
                                        it.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        it.meaning,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.align(
                                            Alignment.Start
                                        )
                                    )
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        it.arabicName,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.align(
                                            Alignment.End
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        "Juz: " + it.juz + " " + "Ruku: " + it.ruku + " " + "Ayah: " + it.ayaat,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        modifier = Modifier.align(
                                            Alignment.End
                                        )
                                    )
                                }
                            }
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Next",
                                tint = Color(0xffCD0000),  // You can customize the color here
                                modifier = Modifier.size(24.dp)  // You can customize the size here
                            )
                        }
                    }

                }
            } else {
                Text("No Data Found", fontSize = 18.sp, color = Color.Black)
            }


        }


    }

}

fun stringToList(input: String): List<String> {
    return input.split(",").map { it }
}

fun surahList(surahs: String): List<Surah> {
    val surahList = mutableListOf<Surah>()
    val numberList = stringToList(surahs)
    surahList.clear()
    println(numberList)
    numberList.forEach { number ->
        when (number) {
            "1" -> surahList.add(
                Surah(
                    1,
                    "Al-Fatihah",
                    "The Opening",
                    "1",
                    "سورة الفاتحة",
                    "1",
                    "7"
                )
            )

            "2" -> surahList.add(
                Surah(
                    2,
                    "Al-Baqarah",
                    "The Cow",
                    "1-2-3",
                    "سورة البقرة",
                    "40",
                    "286"
                )
            )

            "3" -> surahList.add(
                Surah(
                    3,
                    "Al-'Imran",
                    "The Family of Imran",
                    "3-4",
                    "سورة آل عمران",
                    "20",
                    "200"
                )
            )

            "4" -> surahList.add(
                Surah(
                    4,
                    "An-Nisa'",
                    "The Women",
                    "4-5-6",
                    "سورة النساء",
                    "24",
                    "176"
                )
            )

            "5" -> surahList.add(
                Surah(
                    5,
                    "Al-Ma'idah",
                    "The Table Spread",
                    "6-7",
                    "سورة المائدة",
                    "16",
                    "120"
                )
            )

            "6" -> surahList.add(
                Surah(
                    6,
                    "Al-An'am",
                    "The Cattle",
                    "7-8",
                    "سورة الأنعام",
                    "20",
                    "165"
                )
            )

            "7" -> surahList.add(
                Surah(
                    7,
                    "Al-A'raf",
                    "The Heights",
                    "8-9",
                    "سورة الأعراف",
                    "24",
                    "206"
                )
            )

            "8" -> surahList.add(
                Surah(
                    8,
                    "Al-Anfal",
                    "The Spoils of War",
                    "9-10",
                    "سورة الأنفال",
                    "10",
                    "75"
                )
            )

            "9" -> surahList.add(
                Surah(
                    9,
                    "At-Tawbah",
                    "The Repentance",
                    "10-11",
                    "سورة التوبة",
                    "16",
                    "129"
                )
            )

            "10" -> surahList.add(Surah(10, "Yunus", "Jonah", "11", "سورة يونس", "11", "109"))
            "11" -> surahList.add(Surah(11, "Hud", "Hud", "11-12", "سورة هود", "10", "123"))
            "12" -> surahList.add(Surah(12, "Yusuf", "Joseph", "12-13", "سورة يوسف", "12", "111"))
            "13" -> surahList.add(
                Surah(
                    13,
                    "Ar-Ra'd",
                    "The Thunder",
                    "13",
                    "سورة الرّعد",
                    "6",
                    "43"
                )
            )

            "14" -> surahList.add(Surah(14, "Ibrahim", "Abraham", "13", "سورة إبراهيم", "7", "52"))
            "15" -> surahList.add(
                Surah(
                    15,
                    "Al-Hijr",
                    "The Rocky Tract",
                    "13-14",
                    "سورة الحجر",
                    "6",
                    "99"
                )
            )

            "16" -> surahList.add(Surah(16, "An-Nahl", "The Bee", "14", "سورة النحل", "16", "128"))
            "17" -> surahList.add(
                Surah(
                    17,
                    "Al-Isra",
                    "The Night Journey",
                    "15",
                    "سورة الإسراء",
                    "12",
                    "111"
                )
            )

            "18" -> surahList.add(
                Surah(
                    18,
                    "Al-Kahf",
                    "The Cave",
                    "15-16",
                    "سورة الكهف",
                    "12",
                    "110"
                )
            )

            "19" -> surahList.add(Surah(19, "Maryam", "Mary", "16", "سورة مريم", "6", "98"))
            "20" -> surahList.add(Surah(20, "Taha", "Taha", "16", "سورة طه", "8", "135"))
            "21" -> surahList.add(
                Surah(
                    21,
                    "Al-Anbiya'",
                    "The Prophets",
                    "17",
                    "سورة الأنبياء",
                    "7",
                    "112"
                )
            )

            "22" -> surahList.add(
                Surah(
                    22,
                    "Al-Hajj",
                    "The Pilgrimage",
                    "17",
                    "سورة الحج",
                    "10",
                    "78"
                )
            )

            "23" -> surahList.add(
                Surah(
                    23,
                    "Al-Mu'minun",
                    "The Believers",
                    "18",
                    "سورة المؤمنون",
                    "6",
                    "118"
                )
            )

            "24" -> surahList.add(Surah(24, "An-Nur", "The Light", "18", "سورة النّور", "9", "64"))
            "25" -> surahList.add(
                Surah(
                    25,
                    "Al-Furqan",
                    "The Criterion",
                    "18-19",
                    "سورة الفرقان",
                    "6",
                    "77"
                )
            )

            "26" -> surahList.add(
                Surah(
                    26,
                    "Ash-Shu'ara'",
                    "The Poets",
                    "19",
                    "سورة الشعراء",
                    "11",
                    "227"
                )
            )

            "27" -> surahList.add(Surah(27, "An-Naml", "The Ant", "19-20", "سورة النمل", "7", "93"))
            "28" -> surahList.add(
                Surah(
                    28,
                    "Al-Qasas",
                    "The Stories",
                    "20",
                    "سورة القصص",
                    "9",
                    "88"
                )
            )

            "29" -> surahList.add(
                Surah(
                    29,
                    "Al-'Ankabut",
                    "The Spider",
                    "20-21",
                    "سورة العنكبوت",
                    "7",
                    "69"
                )
            )

            "30" -> surahList.add(Surah(30, "Ar-Rum", "The Romans", "21", "سورة الروم", "6", "60"))
            "31" -> surahList.add(Surah(31, "Luqman", "Luqman", "21", "سورة لقمان", "4", "34"))
            "32" -> surahList.add(
                Surah(
                    32,
                    "As-Sajdah",
                    "The Prostration",
                    "21",
                    "سورة السجدة",
                    "3",
                    "30"
                )
            )

            "33" -> surahList.add(
                Surah(
                    33,
                    "Al-Ahzab",
                    "The Combined Forces",
                    "21-22",
                    "سورة الأحزاب",
                    "9",
                    "73"
                )
            )

            "34" -> surahList.add(Surah(34, "Al-Saba'", "Sheba", "22", "سورة سبإ", "6", "54"))
            "35" -> surahList.add(
                Surah(
                    35,
                    "Fatir",
                    "The Originator",
                    "22",
                    "سورة فاطر",
                    "5",
                    "45"
                )
            )

            "36" -> surahList.add(Surah(36, "Ya-Sin", "Ya-Sin", "22-23", "سورة يس", "5", "83"))
            "37" -> surahList.add(
                Surah(
                    37,
                    "As-Saffat",
                    "Those who set the Ranks",
                    "23",
                    "سورة الصّافّات",
                    "5",
                    "182"
                )
            )

            "38" -> surahList.add(Surah(38, "Sad", "Sad", "23", "سورة ص", "5", "88"))
            "39" -> surahList.add(
                Surah(
                    39,
                    "Az-Zumar",
                    "The Troops",
                    "23-24",
                    "سورة الزمر",
                    "8",
                    "75"
                )
            )

            "40" -> surahList.add(Surah(40, "Ghafir", "The Forgiver", "24", "سورة غافر", "9", "85"))
            "41" -> surahList.add(
                Surah(
                    41,
                    "Fussilat",
                    "Explained in Detail",
                    "24-25",
                    "سورة فصّلت",
                    "6",
                    "54"
                )
            )

            "42" -> surahList.add(
                Surah(
                    42,
                    "Ash-Shura",
                    "The Consultation",
                    "25",
                    "سورة الشورى",
                    "5",
                    "53"
                )
            )

            "43" -> surahList.add(
                Surah(
                    43,
                    "Az-Zukhruf",
                    "The Gold Adornments",
                    "25",
                    "سورة الزخرف",
                    "7",
                    "89"
                )
            )

            "44" -> surahList.add(
                Surah(
                    44,
                    "Ad-Dukhan",
                    "The Smoke",
                    "25",
                    "سورة الدخان",
                    "3",
                    "59"
                )
            )

            "45" -> surahList.add(
                Surah(
                    45,
                    "Al-Jathiyah",
                    "The Crouching",
                    "25",
                    "سورة الجاثية",
                    "4",
                    "37"
                )
            )

            "46" -> surahList.add(
                Surah(
                    46,
                    "Al-Ahqaf",
                    "The Wind-Curved Sandhills",
                    "26",
                    "سورة الأحقاف",
                    "4",
                    "35"
                )
            )

            "47" -> surahList.add(Surah(47, "Muhammad", "Muhammad", "26", "سورة محمّـد", "4", "38"))
            "48" -> surahList.add(
                Surah(
                    48,
                    "Al-Fath",
                    "The Victory",
                    "26",
                    "سورة الفتح",
                    "4",
                    "29"
                )
            )

            "49" -> surahList.add(
                Surah(
                    49,
                    "Al-Hujurat",
                    "The Rooms",
                    "26",
                    "سورة الحُـجُـرات",
                    "2",
                    "18"
                )
            )

            "50" -> surahList.add(Surah(50, "Qaf", "Qaf", "26", "سورة ق", "3", "45"))
            "51" -> surahList.add(
                Surah(
                    51,
                    "Ad-Dhariyat",
                    "The Winnowing Winds",
                    "26-27",
                    "سورة الذاريات",
                    "3",
                    "60"
                )
            )

            "52" -> surahList.add(Surah(52, "At-Tur", "The Mount", "27", "سورة الـطور", "2", "49"))
            "53" -> surahList.add(Surah(53, "An-Najm", "The Star", "27", "سورة الـنجـم", "3", "62"))
            "54" -> surahList.add(
                Surah(
                    54,
                    "Al-Qamar",
                    "The Moon",
                    "27",
                    "سورة الـقمـر",
                    "3",
                    "55"
                )
            )

            "55" -> surahList.add(
                Surah(
                    55,
                    "Ar-Rahman",
                    "The Beneficent",
                    "27",
                    "سورة الـرحـمـن",
                    "3",
                    "78"
                )
            )

            "56" -> surahList.add(
                Surah(
                    56,
                    "Al-Waqi'ah",
                    "The Inevitable",
                    "27",
                    "سورة الواقعة",
                    "3",
                    "96"
                )
            )

            "57" -> surahList.add(
                Surah(
                    57,
                    "Al-Hadid",
                    "The Iron",
                    "27",
                    "سورة الحـديد",
                    "4",
                    "29"
                )
            )

            "58" -> surahList.add(
                Surah(
                    58,
                    "Al-Mujadilah",
                    "The Pleading Woman",
                    "28",
                    "سورة الـمجادلـة",
                    "3",
                    "22"
                )
            )

            "59" -> surahList.add(
                Surah(
                    59,
                    "Al-Hashr",
                    "The Exile",
                    "28",
                    "سورة الـحـشـر",
                    "3",
                    "24"
                )
            )

            "60" -> surahList.add(
                Surah(
                    60,
                    "Al-Mumtahanah",
                    "She that is to be examined",
                    "28",
                    "سورة الـمـمـتـحنة",
                    "2",
                    "13"
                )
            )

            "61" -> surahList.add(
                Surah(
                    61,
                    "As-Saff",
                    "The Ranks",
                    "28",
                    "سورة الـصّـف",
                    "2",
                    "14"
                )
            )

            "62" -> surahList.add(
                Surah(
                    62,
                    "Al-Jumu'ah",
                    "The Congregation",
                    "28",
                    "سورة الـجـمـعـة",
                    "2",
                    "11"
                )
            )

            "63" -> surahList.add(
                Surah(
                    63,
                    "Al-Munafiqun",
                    "The Hypocrites",
                    "28",
                    "سورة الـمنافقون",
                    "2",
                    "11"
                )
            )

            "64" -> surahList.add(
                Surah(
                    64,
                    "At-Taghabun",
                    "The Mutual Disillusion",
                    "28",
                    "سورة الـتغابن",
                    "2",
                    "18"
                )
            )

            "65" -> surahList.add(
                Surah(
                    65,
                    "At-Talaq",
                    "The Divorce",
                    "28",
                    "سورة الـطلاق",
                    "2",
                    "12"
                )
            )

            "66" -> surahList.add(
                Surah(
                    66,
                    "At-Tahrim",
                    "The Prohibition",
                    "28",
                    "سورة الـتحريم",
                    "2",
                    "12"
                )
            )

            "67" -> surahList.add(
                Surah(
                    67,
                    "Al-Mulk",
                    "The Sovereignty",
                    "29",
                    "سورة الـملك",
                    "2",
                    "30"
                )
            )

            "68" -> surahList.add(
                Surah(
                    68,
                    "Al-Qalam",
                    "The Pen",
                    "29",
                    "سورة الـقـلـم",
                    "2",
                    "52"
                )
            )

            "69" -> surahList.add(
                Surah(
                    69,
                    "Al-Haqqah",
                    "The Reality",
                    "29",
                    "سورة الـحاقّـة",
                    "2",
                    "52"
                )
            )

            "70" -> surahList.add(
                Surah(
                    70,
                    "Al-Ma'arij",
                    "The Ascending Stairways",
                    "29",
                    "سورة الـمعارج",
                    "2",
                    "44"
                )
            )

            "71" -> surahList.add(Surah(71, "Nuh", "Noah", "29", "سورة نوح", "2", "28"))
            "72" -> surahList.add(Surah(72, "Al-Jinn", "The Jinn", "29", "سورة الجن", "2", "28"))
            "73" -> surahList.add(
                Surah(
                    73,
                    "Al-Muzzammil",
                    "The Enshrouded One",
                    "29",
                    "سورة الـمـزّمّـل",
                    "2",
                    "20"
                )
            )

            "74" -> surahList.add(
                Surah(
                    74,
                    "Al-Muddaththir",
                    "The Cloaked One",
                    "29",
                    "سورة الـمّـدّثّـر",
                    "2",
                    "56"
                )
            )

            "75" -> surahList.add(
                Surah(
                    75,
                    "Al-Qiyamah",
                    "The Resurrection",
                    "29",
                    "سورة الـقـيامـة",
                    "2",
                    "40"
                )
            )

            "76" -> surahList.add(Surah(76, "Al-Insan", "Man", "29", "سورة الإنسان", "2", "31"))
            "77" -> surahList.add(
                Surah(
                    77,
                    "Al-Mursalat",
                    "The Emissaries",
                    "29",
                    "سورة الـمرسلات",
                    "2",
                    "50"
                )
            )

            "78" -> surahList.add(
                Surah(
                    78,
                    "An-Naba'",
                    "The Tidings",
                    "30",
                    "سورة الـنبإ",
                    "2",
                    "40"
                )
            )

            "79" -> surahList.add(
                Surah(
                    79,
                    "An-Nazi'at",
                    "Those who drag forth",
                    "30",
                    "سورة الـنازعات",
                    "2",
                    "46"
                )
            )

            "80" -> surahList.add(Surah(80, "'Abasa", "He frowned", "30", "سورة عبس", "1", "42"))
            "81" -> surahList.add(
                Surah(
                    81,
                    "At-Takwir",
                    "The Overthrowing",
                    "30",
                    "سورة التكوير",
                    "1",
                    "29"
                )
            )

            "82" -> surahList.add(
                Surah(
                    82,
                    "Al-Infitar",
                    "The Cleaving",
                    "30",
                    "سورة الانفطار",
                    "1",
                    "19"
                )
            )

            "83" -> surahList.add(
                Surah(
                    83,
                    "Al-Mutaffifin",
                    "Defrauding",
                    "30",
                    "سورة المطـفـفين",
                    "1",
                    "36"
                )
            )

            "84" -> surahList.add(
                Surah(
                    84,
                    "Al-Inshiqaq",
                    "The Splitting Open",
                    "30",
                    "سورة الانشقاق",
                    "1",
                    "25"
                )
            )

            "85" -> surahList.add(
                Surah(
                    85,
                    "Al-Buruj",
                    "The Mansions of the Stars",
                    "30",
                    "سورة البروج",
                    "1",
                    "22"
                )
            )

            "86" -> surahList.add(
                Surah(
                    86,
                    "At-Tariq",
                    "The Morning Star",
                    "30",
                    "سورة الـطارق",
                    "1",
                    "17"
                )
            )

            "87" -> surahList.add(
                Surah(
                    87,
                    "Al-A'la",
                    "The Most High",
                    "30",
                    "سورة الأعـلى",
                    "1",
                    "19"
                )
            )

            "88" -> surahList.add(
                Surah(
                    88,
                    "Al-Ghashiyah",
                    "The Overwhelming",
                    "30",
                    "سورة الغاشـيـة",
                    "1",
                    "26"
                )
            )

            "89" -> surahList.add(Surah(89, "Al-Fajr", "The Dawn", "30", "سورة الفجر", "1", "30"))
            "90" -> surahList.add(Surah(90, "Al-Balad", "The City", "30", "سورة الـبلد", "1", "20"))
            "91" -> surahList.add(
                Surah(
                    91,
                    "Ash-Shams",
                    "The Sun",
                    "30",
                    "سورة الـشـمـس",
                    "1",
                    "15"
                )
            )

            "92" -> surahList.add(
                Surah(
                    92,
                    "Al-Lail",
                    "The Night",
                    "30",
                    "سورة اللـيـل",
                    "1",
                    "21"
                )
            )

            "93" -> surahList.add(
                Surah(
                    93,
                    "Ad-Duha",
                    "The Morning Hours",
                    "30",
                    "سورة الضـحى",
                    "1",
                    "11"
                )
            )

            "94" -> surahList.add(
                Surah(
                    94,
                    "Ash-Sharh",
                    "The Relief",
                    "30",
                    "سورة الـشرح",
                    "1",
                    "8"
                )
            )

            "95" -> surahList.add(Surah(95, "At-Tin", "The Fig", "30", "سورة الـتين", "1", "8"))
            "96" -> surahList.add(Surah(96, "Al-'Alaq", "The Clot", "30", "سورة الـعلق", "1", "19"))
            "97" -> surahList.add(Surah(97, "Al-Qadr", "The Power", "30", "سورة الـقدر", "1", "5"))
            "98" -> surahList.add(
                Surah(
                    98,
                    "Al-Bayyinah",
                    "The Clear Proof",
                    "30",
                    "سورة الـبينة",
                    "1",
                    "8"
                )
            )

            "99" -> surahList.add(
                Surah(
                    99,
                    "Az-Zalzalah",
                    "The Earthquake",
                    "30",
                    "سورة الـزلزلة",
                    "1",
                    "8"
                )
            )

            "100" -> surahList.add(
                Surah(
                    100,
                    "Al-'Adiyat",
                    "The Courser",
                    "30",
                    "سورة الـعاديات",
                    "1",
                    "11"
                )
            )

            "101" -> surahList.add(
                Surah(
                    101,
                    "Al-Qari'ah",
                    "The Calamity",
                    "30",
                    "سورة الـقارعـة",
                    "1",
                    "11"
                )
            )

            "102" -> surahList.add(
                Surah(
                    102,
                    "At-Takathur",
                    "The Rivalry in World Increase",
                    "30",
                    "سورة الـتكاثر",
                    "1",
                    "8"
                )
            )

            "103" -> surahList.add(
                Surah(
                    103,
                    "Al-'Asr",
                    "The Declining Day",
                    "30",
                    "سورة الـعصر",
                    "1",
                    "3"
                )
            )

            "104" -> surahList.add(
                Surah(
                    104,
                    "Al-Humazah",
                    "The Traducer",
                    "30",
                    "سورة الـهمزة",
                    "1",
                    "9"
                )
            )

            "105" -> surahList.add(
                Surah(
                    105,
                    "Al-Fil",
                    "The Elephant",
                    "30",
                    "سورة الـفيل",
                    "1",
                    "5"
                )
            )

            "106" -> surahList.add(
                Surah(
                    106,
                    "Al-Quraish",
                    "The Quraysh",
                    "30",
                    "سورة قريش",
                    "1",
                    "4"
                )
            )

            "107" -> surahList.add(
                Surah(
                    107,
                    "Al-Ma'un",
                    "The Small Kindnesses",
                    "30",
                    "سورة المـاعون",
                    "1",
                    "7"
                )
            )

            "108" -> surahList.add(
                Surah(
                    108,
                    "Al-Kauthar",
                    "The Abundance",
                    "30",
                    "سورة الـكوثر",
                    "1",
                    "3"
                )
            )

            "109" -> surahList.add(
                Surah(
                    109,
                    "Al-Kafirun",
                    "The Disbelievers",
                    "30",
                    "سورة الـكافرون",
                    "1",
                    "6"
                )
            )

            "110" -> surahList.add(
                Surah(
                    110,
                    "An-Nasr",
                    "The Divine Support",
                    "30",
                    "سورة الـنصر",
                    "1",
                    "3"
                )
            )

            "111" -> surahList.add(
                Surah(
                    111,
                    "Al-Masad",
                    "The Palm Fiber",
                    "30",
                    "سورة الـمسد",
                    "1",
                    "5"
                )
            )

            "112" -> surahList.add(
                Surah(
                    112,
                    "Al-Ikhlas",
                    "The Sincerity",
                    "30",
                    "سورة الإخلاص",
                    "1",
                    "4"
                )
            )

            "113" -> surahList.add(
                Surah(
                    113,
                    "Al-Falaq",
                    "The Daybreak",
                    "30",
                    "سورة الـفلق",
                    "1",
                    "5"
                )
            )

            "114" -> surahList.add(
                Surah(
                    114,
                    "An-Nas",
                    "The Mankind",
                    "30",
                    "سورة الـناس",
                    "1",
                    "6"
                )
            )
        }
    }

    return surahList
}