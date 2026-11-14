# [El. dienynas su DI mokymosi asistentu]

Kursinio darbo I dalis: projektavimo dokumentas

## 1. Problema ir idėja

**Sistema vienu sakiniu:** [Sistema skirta mokiniams, mokytojams, kad būtų galima vienoje vietoje valdyti pažymius, namų darbus, atsiskaitymus ir pasinaudoti DI asistentu mokymosi planavimui.]

**Problema ir dabartinis procesas:** [Mokiniui informacija apie mokymąsi dažnai yra išskaidyta: pažymiai, namų darbai ir atsiskaitymai pateikiami skirtingose sistemos vietose. Dėl to gali būti sunku greitai suprasti, ką reikia atlikti ir kam reikėtų skirti daugiau dėmesio. Įprastas el. dienynas daugiausia pateikia informaciją, tačiau nepadeda jos interpretuoti ir sudaryti individualaus mokymosi plano.]

**Nauda:** [Sistema leis mokiniui vienoje vietoje matyti svarbiausią mokymosi informaciją ir gauti individualizuotus pasiūlymus. DI asistentas galės pagal mokinio užduotis, artėjančius atsiskaitymus ir kitą sistemoje esančią informaciją pasiūlyti, ką mokiniui reikėtų atlikti ar pasikartoti.]

**Naudotojai:** [Mokinys - peržiūri pažymius, namų darbus, atsiskaitymus ir naudojasi DI asistentu.
Mokytojas - gali įvesti pažymius, sukurti namų darbus su atlikimo terminu ir atsiskaitymus su data.]

**Prielaidos:** [Sistema bus kuriama kaip internetinė aplikacija.
Naudotojai bus sukurti iš anksto.
Naudotojai turės paskyras ir galės prisijungti prie sistemos.
Mokymosi informacija bus saugoma duomenų bazėje.
DI asistentas turės apdoroti pateikiamą informaciją ir pagal ją pateikti atsakymą.]

## 2. Apimtis

| Funkcija                 | Ką naudotojas galės atlikti                                                                 | Pagrindinis modulis ar pagalbinė funkcija |
| ------------------------ | ------------------------------------------------------------------------------------------- | ----------------------------------------- |
| [Prisijungimas]          | [Prisijungti prie sistemos pagal savo paskyrą ir gauti atitinkamas teises]                  | [Pagalbinė funkcija]                      |
| [Pažymiai]               | [Peržiūrėti savo dalykų pažymius ir vidurkius]                                              | [Pagalbinė funkcija]                      |
| [Namų darbai]            | [Peržiūrėti užduotis, jų terminus]                                                          | [Pagalbinė funkcija]                      |
| [Atsiskaitymai]          | [Matyti artėjančius kontrolinius darbus, testus ir kitus atsiskaitymus]                     | [Pagalbinė funkcija]                      |
| [DI mokymosi asistentas] | [Užduoti klausimus apie savo mokymosi planą ir gauti rekomendacijas pagal dienyno duomenis] | [Pagrindinis modulis]                     |
| [Mokytojo funkcijos]     | [Įvesti pažymius, namų darbus ir atsiskaitymus]                                             | [Pagalbinė funkcija]                      |

**Į kursinio darbo apimtį neįeina:** [Nebus naudotojų valdymo funkcijos (naudotojo administratoriaus, kuris gali priskirti mokinius ir mokytojus prie klasių, sukurti/ištrinti jų paskyras ir t.t.), pradininiai duomenys bus suvesti iš anksto duomenų bazėje, nebus pamokų tvarkaraščio]

## 3. Pagrindinis modulis

**DI mokymosi asistentas:** [Modulis skirtas padėti mokiniui suprasti, ką jam reikėtų atlikti ir kaip planuoti mokymąsi. Asistentas analizuos sistemoje esančius mokinio duomenis, tokius kaip namų darbai, artėjantys atsiskaitymai ir pažymiai, ir pagal juos pateiks atsakymą.]

**Logika, kurią reikės projektuoti ir testuoti:** [Pagrindinė logika bus atsakinga už naudotojo klausimo apdorojimą, tinkamų duomenų iš dienyno parinkimą, prioritetų nustatymą, konteksto paruošimą DI modeliui, DI atsakymo gavimą ir gauto atsakymo tinkamumo patikrinimą.]

**Įvestis:** [Naudotojo klausimas ir su juo susiję dienyno duomenys, pvz.: „Ką man reikėtų šiandien pasimokyti?“, sistemoje tuo metu yra nurodytas matematikos kontrolinis už dienos, matematikos vidurkis 6, anglų kalbos namų darbai turi būti paruošti iki rytojaus]

**Išvestis:** [DI asistento atsakymas su prioritetine mokymosi rekomendacija, pvz.: „Šiandien pirmiausia rekomenduočiau skirti laiką matematikai, nes rytoj laukia kontrolinis. Po to užbaik anglų kalbos namų darbus.“]

**Veikimo eiga:** [

1. Mokinys prisijungia prie sistemos.
2. Mokinys pateikia klausimą DI asistentui.
3. Sistema nustato prisijungusio mokinio ID.
4. Iš duomenų bazės gaunami su klausimu susiję mokinio duomenys.
5. Sistema pagal nustatytas taisykles atrenka aktualius duomenis ir nustato jų prioritetą.
6. Sistema suformuoja užklausą DI modeliui.
7. DI modelis sugeneruoja atsakymą.
8. Sistema patikrina, ar atsakymas gautas ir ar jis atitinka nustatytus tinkamumo kriterijus, ar yra laikomasi nustatytų taisyklių.
9. Jei atsakymas tinkamas, jis pateikiamas mokiniui.
10. Jei atsakymas negautas arba netinkamas, mokiniui pateikiamas informacinis pranešimas ir leidžiama užklausą pakartoti.]

### Taisyklės arba sprendimo žingsniai

1. [DI asistentas gali naudoti tik prie prisijungusio mokinio paskyros susietus duomenis.]
2. [Artimiausiam atsiskaitymui ar namų darbui suteikiamas didesnis prioritetas už vėliau vyksiantį atsiskaitymą ar vėlesnį namų darbo terminą.]
3. [Jei tą pačią dieną yra keli atsiskaitymai ir (ar) namų darbai, rekomendacijoje turi būti atsižvelgiama į visus tos dienos darbus ir siūloma tinkamai paskirstyti jiems skirtą laiką.]
4. [Jei artėjančių atsiskaitymų nėra, rekomendacija formuojama pagal artimiausius namų darbų terminus.]
5. [Jei nėra pakankamai dienyno duomenų atsakymui suformuoti, sistema turi aiškiai apie tai informuoti mokinį.]
6. [DI atsakymas turi būti paremtas į DI užklausą perduotu mokinio kontekstu.]
7. [Jei DI paslauga nepasiekiama arba atsakymas negaunamas, sistema turi pateikti suprantamą klaidos pranešimą ir leisti užklausą pakartoti.]
8. [Sistema neturi leisti DI asistentui keisti pažymių ar kitų dienyno duomenų.]

### DI atsakymo tinkamumo vertinimas

[DI atsakymas laikomas tinkamu, jeigu:

1. Atsakymas yra gautas ir nėra tuščias
2. Atsakymas yra susijęs su mokinio pateiktu klausimu
3. Atsakymas remiasi į DI užklausą perduotais dienyno duomenimis
4. Atsakyme nepateikiami kontekste nesantys mokinio dienyno faktai
5. Atsakymas atsižvelgia į nustatytas prioritetų taisykles
6. Sistema palygina DI atsakymą su perduotais dienyno duomenimis. Patikrinama, ar atsakyme nurodyti darbai ir jų terminai yra teisingi bei ar laikomasi nustatytų prioritetų. Jei randama neatitikimų, atsakymas laikomas netinkamu.]

[Vertinant atsakymą naudojami trys rezultatai:

Tinkamas: atsakymas atitinka mokinio klausimą, remiasi pateiktais duomenimis ir nepažeidžia nustatytų taisyklių.
Netinkamas: atsakymas neatitinka pateiktų duomenų, ignoruoja svarbią informaciją arba pateikia išgalvotus faktus.
Negautas: DI paslauga neatsako arba grąžina klaidą. Tokiu atveju mokiniui parodomas informacinis pranešimas ir nepateikiamas išgalvotas atsakymas.]

[Jeigu atsakymas neatitinka nustatytų tinkamumo kriterijų, jis nelaikomas tinkamu ir mokiniui nepateikiamas kaip galutinis atsakymas. Sistema parodo informacinį pranešimą ir leidžia užklausą pakartoti.]

### Scenarijai būsimiems testams

| Scenarijus                        | Pradinės sąlygos ir konkreti įvestis                                          | Veiksmas                                    | Tikslus laukiamas rezultatas                                                                                                                                                                                         |
| --------------------------------- | ----------------------------------------------------------------------------- | ------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Įprastas atvejis                  | [Mokinys turi kontrolinį rytoj ir kelias namų darbų užduotis]                 | [Įveda „Ką man šiandien reikėtų padaryti?“] | [Sistema pateikia rekomendaciją, kurioje pirmenybė teikiama artėjančiam kontroliniam ir artimiausiems terminams]                                                                                                     |
| Ribinis atvejis arba konfliktas   | [Du atsiskaitymai vyksta tą pačią dieną]                                      | [Įveda „Kam skirti daugiau laiko?“]         | [Jeigu keli atsiskaitymai vyksta tą pačią dieną, sistema nelaiko vieno iš jų automatiškai svarbesniu. DI rekomendacijoje turi būti paminėti abu atsiskaitymai ir rekomenduojama paskirstyti mokymosi laiką tarp jų.] |
| Klaida arba neįmanomas rezultatas | [DI paslauga nepasiekiama]                                                    | [Mokinys pateikia klausimą]                 | [Sistema pateikia klaidos pranešimą ir nesugeneruoja melagingo atsakymo]                                                                                                                                             |
| Netinkamas atsakymas              | [DI atsakyme nurodomas testiniuose duomenyse nesantis atsiskaitymas]          | [Sistema gauna DI atsakymą]                 | [Atsakymas laikomas netinkamu ir mokiniui nepateikiamas kaip galutinis atsakymas, atvaizduojamas klaidos pranešimas]                                                                                                 |
| Nėra atsiskaitymų                 | [Artėjančių atsiskaitymų nėra, tačiau yra namų darbas su artimiausiu terminu] | [Klausia, ką atlikti pirmiausia]            | [Atsakyme pirmenybė teikiama artimiausiam namų darbo terminui]                                                                                                                                                       |
| Nėra atsiskaitymų ir namų darbų   | [Nėra artėjančių atsiskaitymų ir namų darbų]                                  | [Klausia, kokius darbus reikia atlikti]     | [Atsakyme pranešama apie tai, kad artėjančių darbų nėra]                                                                                                                                                             |

[DI užklausai bus pateikiamas aiškiai suformuotas kontekstas: naudotojo klausimas, aktualūs namų darbai, atsiskaitymai ir kiti reikalingi duomenys.

Testavimui bus naudojami iš anksto paruošti scenarijai su žinomais duomenimis. Bus tikrinama, ar naudotojo užklausa tinkamai perduodama DI modeliui, ar gaunamas atsakymas ir ar atsakyme naudojami konkretaus naudotojo pateikti bei sistemoje esantys duomenys. Taip pat bus tikrinama, ar vienas naudotojas negali gauti kito mokinio informacijos.

Jeigu DI paslauga nepasiekiama arba negaunamas atsakymas, sistema turi informuoti naudotoją apie laikiną DI asistento nepasiekiamumą.]

### Pavyzdiniai DI testavimo duomenys

[Visuose testuose „šiandien“ data laikoma 2026-10-08. Darbų prioritetai nustatomi pagal jų terminus, lyginant su šia data.]

| Duomenys                         | Reikšmė      |
| -------------------------------- | ------------ |
| [Matematikos kontrolinis darbas] | [2026-10-09] |
| [Anglų kalbos namų darbas]       | [2026-10-10] |
| [Istorijos kontrolinis darbas]   | [2026-10-15] |

[Mokinio klausimas: „Ką man šiandien pirmiausia reikėtų atlikti?“]

[Pagal nustatytas taisykles tikimasi, kad atsakyme pirmenybė bus teikiama artimiausiam matematikos kontroliniam, o vėliau bus paminėtas anglų kalbos namų darbas.]

| Duomenys                       | Reikšmė      |
| ------------------------------ | ------------ |
| [Anglų kalbos namų darbas]     | [2026-10-10] |
| [Istorijos kontrolinis darbas] | [2026-10-12] |

[Mokinio klausimas: „Ką turėčiau daryti šiandien?“]

[Pagal nustatytas taisykles tikimasi, kad atsakyme pirmenybė bus teikiama artimiausiam anglų kalbos namų darbui, o vėliau bus paminėtas istorijos kontrolinis darbas.]

| Duomenys | Reikšmė |
| -------- | ------- |
|          |         |

[Mokinio klausimas: „Ką turėčiau atlikti?“]

[Pagal nustatytas taisykles tikimasi, kad atsakyme bus pasakyta, kad nėra artėjančių darbų.]

| Duomenys                          | Reikšmė      |
| --------------------------------- | ------------ |
| [Anglų kalbos kontrolinis darbas] | [2026-10-12] |
| [Istorijos kontrolinis darbas]    | [2026-10-12] |

[Mokinio klausimas: „Kam turėčiau pasiruošti artimiausiu metu?“]

[Pagal nustatytas taisykles tikimasi, kad atsakyme bus paminėti visi kontroliniai darbai, vykstantis tą pačią dieną ir siūloma paskirstyti laiką tarp jų.]

## 4. Kokybės atributas

**Pasirinktas atributas:** [Naudojamumas (Usability).]

**Kodėl svarbus šiai sistemai:** [El. dienynu naudosis mokiniai ir mokytojai, todėl sistema turi būti lengvai suprantama ir patogi naudoti. Naudotojas turėtų greitai rasti pažymius, namų darbus, atsiskaitymus bei DI asistentą ir neturėtų būti priverstas atlikti daug nereikalingų veiksmų.]

**Tikrinimo scenarijus ir sąlygos:** [Bus pasirinktos pagrindinės naudotojo užduotys: prisijungti prie sistemos, rasti artėjantį atsiskaitymą, peržiūrėti namų darbus ir pateikti klausimą DI asistentui. Užduotis atliks keli testuotojai, kurie nėra kūrę sistemos.]

**Sėkmės kriterijus:** [Bent 3 iš 4 testuotojų turi savarankiškai atlikti visas pagrindines užduotis be papildomo paaiškinimo.]

**Numatytas projektavimo sprendimas:** [Bus naudojama paprasta ir nuosekli vartotojo sąsaja. Pagrindinės funkcijos bus pasiekiamos iš pagrindinio meniu, o svarbiausia informacija - artėjantys atsiskaitymai ir namų darbai - bus rodoma pagrindiniame puslapyje.]

**Kaip patikrinsiu vėlesniame etape:** [Atliksiu naudotojų testavimą. Testuotojams pateiksiu kelias konkrečias užduotis ir stebėsiu, ar jie gali jas atlikti be pagalbos. Taip pat užfiksuosiu dažniausiai pasitaikančias klaidas ir naudotojų pastabas.]

**Sprendimo kaina arba ribojimas:** [Naudojamumo testavimas reikalauja papildomo laiko ir kelių žmonių, kurie išbandytų sistemą. Be to, skirtingi naudotojai gali skirtingai vertinti vartotojo sąsajos patogumą.]

## 5. Pradinė sistemos struktūra

### Paprasta schema

```text
                  ┌─────────────────────┐
                  │       Mokinys       │
                  │    / Mokytojas      │
                  └──────────┬──────────┘
                             │
                             ▼
                  ┌─────────────────────┐
                  │   Web vartotojo     │
                  │      sąsaja         │
                  └──────────┬──────────┘
                             │ HTTP/REST
                             ▼
                  ┌─────────────────────┐
                  │   Java / Spring     │
                  │      Boot           │
                  ├─────────────────────┤
                  │ Verslo logika       │
                  │ Autentifikacija     │
                  │ DI asistento modulis│
                  └───────┬───────┬─────┘
                          │       │
                ┌─────────┘       └──────────┐
                ▼                            ▼
      ┌──────────────────┐        ┌──────────────────┐
      │   PostgreSQL     │        │   DI modelis     │
      │    duomenų bazė  │        │ Ollama / API     │
      └──────────────────┘        └──────────────────┘
```

| Sistemos dalis              | Atsakomybė                                                                                                           |
| --------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| [Web vartotojo sąsaja]      | [Atvaizduoti dienyno informaciją ir suteikti galimybę naudotojui sąveikauti su sistema]                              |
| [Java, Spring Boot backend] | [Apdoroti užklausas, vykdyti verslo logiką, autentifikuoti naudotojus ir komunikuoti su duomenų baze bei DI modeliu] |
| [PostgreSQL]                | [Saugoti naudotojų, pažymių, namų darbų, atsiskaitymų ir kitus sistemos duomenis]                                    |
| [DI asistento modulis]      | [Paruošti duomenis DI modeliui, išsiųsti užklausą ir atvaizduoti gautą atsakymą]                                     |
| [DI modelis]                | [Pagal pateiktą kontekstą generuoti natūralios kalbos atsakymą]                                                      |

**Planuojamos technologijos ir pasirinkimo priežastys:** [Java - pagrindinė programavimo kalba. Pasirinkta todėl, kad palaiko objektinį programavimą ir yra tinkama žiniatinklio sistemų kūrimui.
Spring Boot - bus naudojama serverinei sistemos daliai ir REST API kūrimui. Pasirinkta todėl, kad supaprastina Java pagrindu kuriamų žiniatinklio aplikacijų kūrimą.
PostgreSQL - bus naudojama sistemos duomenims saugoti. Pasirinkta dėl patikimo reliacinių duomenų valdymo, duomenų vientisumo užtikrinimo ir tinkamumo tokio tipo sistemoms.
HTML, CSS ir JavaScript - pradinei vartotojo sąsajai. Pasirinkti todėl, kad leidžia sukurti paprastą ir funkcionalią žiniatinklio sąsają be papildomos sudėtingos frontend technologijos.
Ollama - lokalaus DI modelio paleidimui ir integracijai kūrimo bei testavimo metu. Pasirinkta todėl, kad leidžia kūrimo ir testavimo metu naudoti DI modelį lokaliai, nenaudojant išorinės mokamos DI paslaugos.
GitHub - programinio kodo versijavimui ir projekto saugojimui. Pasirinktas todėl, kad leidžia sekti kodo pakeitimus, išsaugoti ankstesnes projekto versijas ir palengvina projekto kūrimo procesą.]

## 6. AI panaudojimas

### AI rengiant šį dokumentą

| Priemonė ir užduotis                           | Ką panaudojau                                         | Ką atmečiau arba perrašiau ir kodėl                                                                      | Kaip patikrinau                                                                                                                                                                                                                              |
| ---------------------------------------------- | ----------------------------------------------------- | -------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [ChatGPT – projekto funkcijų planavimas]       | [Pasiūlymus dėl sistemos modulių ir funkcijų]         | [Atmestos per sudėtingos funkcijos, pavyzdžiui, pažangos analizė, nes projekto apimtis būtų per didelė]  | [Įvertinau, ar funkcijos realiai įgyvendinamos per numatytą projekto laiką]                                                                                                                                                                  |
| [ChatGPT – dokumento tekstų rengimas]          | [Pasiūlytas formuluotes ir tekstų struktūrą]          | [Kai kurios formuluotės buvo supaprastintos arba perrašytos, kad atitiktų projekto sprendimus ir apimtį] | [Palyginau su savo projekto sprendimais ir pakoregavau tekstą]                                                                                                                                                                               |
| [ChatGPT – technologijų pasirinkimo aprašymas] | [Technologijų aprašymus ir jų pasirinkimo priežastis] | [Per daug techninės arba nereikalingos informacijos buvo atsisakyta]                                     | [Patikrinau, ar pasirinktos technologijos atitinka planuojamą sistemos architektūrą]                                                                                                                                                         |
| [ChatGPT – DI funkcionalumo planavimas]        | [DI asistento veikimo ir testavimo idėjas]            | [Atsisakyta sudėtingo DI atsakymų kokybės vertinimo, nes projekto apimtis tam per didelė]                | [Atsisakyta sudėtingo DI atsakymų kokybės vertinimo, tačiau bus tikrinama, ar atsakymai teisingai remiasi dienyno duomenimis, nurodo terminus ir laikosi prioritetų. Tai bus atliekama naudojant 3 skyriuje aprašytus testavimo scenarijus.] |

### Planuojamas AI naudojimas kuriant sistemą

**Kur ir kam naudosiu AI:** [AI bus naudojamas kaip pagalbinė priemonė programuojant, ieškant galimų klaidų, generuojant testų idėjas, aiškinantis Spring Boot technologiją, PostgreSQL implementavimą ir kuriant DI asistento funkcionalumą.]

**Kaip tikrinsiu pasiūlymus ir sugeneruotą kodą:** [Sugeneruotas kodas bus naudojamas tik jeigu yra suprantamas, paleidžiamas ir tikrinamas jo veikimas]

**Ar AI bus sistemos funkcionalumo dalis:** [Taip. DI bus naudojamas kaip mokymosi asistentas, kuris pagal mokinio dienyno duomenis galės pateikti mokymosi planavimo ir užduočių prioritetų rekomendacijas]

## 7. Tolesnių darbų planas

| Darbas                                 | Apčiuopiamas rezultatas                                                             | Planuojama darbų seka |
| -------------------------------------- | ----------------------------------------------------------------------------------- | --------------------- |
| [Projekto sukūrimas]                   | [Veikiantis Spring Boot projektas]                                                  | [1]                   |
| [DI asistento prototipas]              | [Java aplikacija, galinti siųsti užklausas DI modeliui ir rodyti atsakymą]          | [2]                   |
| [Duomenų bazės projektavimas]          | [PostgreSQL lentelės naudotojams, dalykams, pažymiams, užduotims ir atsiskaitymams] | [3]                   |
| [Autentifikacija]                      | [Veikiantis mokinio ir mokytojo prisijungimas]                                      | [4]                   |
| [Dienyno funkcijos]                    | [Pažymių, namų darbų ir atsiskaitymų peržiūra]                                      | [5]                   |
| [DI integracija su dienyno duomenimis] | [Asistentas gali atsakyti pagal konkretaus mokinio informaciją]                     | [6]                   |
| [Testavimas]                           | [Rankinio testavimo scenarijų rinkinys]                                             | [7]                   |
| [Vartotojo sąsajos patobulinimas]      | [Patogus pagrindinis puslapis ir DI asistento langas]                               | [8]                   |

**Būsimo prototipo veikimo scenarijus:** [Mokinys pateiks klausimą DI mokymosi asistentui, pavyzdžiui: „Ką man pirmiausia reikėtų atlikti?“

DI užklausai bus pateikiamas mokinio klausimas ir keli aktualūs duomenys iš sistemos, pavyzdžiui, artėjantis atsiskaitymas bei paskirtas namų darbas.

DI asistentas pateiks mokiniui suprantamą rekomendaciją, paremtą pateiktais duomenimis. Pavyzdžiui, jei atsiskaitymas yra artimesnis už namų darbo terminą, DI gali rekomenduoti pirmiausia skirti laiko pasiruošimui atsiskaitymui.]

| Rizika arba neaiškumas                                        | Kaip patikrinsiu arba sumažinsiu                                                                                 |
| ------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- |
| [Lokaliojo DI modelio atsakymai gali būti per lėti]           | [Išbandysiu kelis mažesnius modelius ir patikrinsiu atsakymo laiką]                                              |
| [DI gali pateikti išgalvotą informaciją]                      | [DI užklausose pateiksiu aiškų kontekstą ir testuosiu su iš anksto žinomais duomenimis]                          |
| [DI gali neteisingai interpretuoti mokinio duomenis]          | [Naudosiu konkrečius testinius scenarijus ir tikrinsiu atsakymus]                                                |
| [Naudotojo duomenys gali būti pasiekiami netinkamam asmeniui] | [Įgyvendinsiu autentifikaciją ir tikrinsiu, kad backend užklausos grąžintų tik prisijungusio naudotojo duomenis] |

## Šaltiniai

[Nenaudota]
