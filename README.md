# SOPT27_Android

### 📌 Assignment01 20201105
<img src = "https://user-images.githubusercontent.com/50744222/98365459-ce56f580-2075-11eb-9c9f-352bbb0488fe.gif" 
width = "30%"/>
<img src = "https://user-images.githubusercontent.com/50744222/98365586-fba3a380-2075-11eb-9fb1-69b10ef2d53f.gif"
width = "30%" />  
### 📑 MUST - SignUpActivity
🔥 로그인 화면에서 회원가입을 누르면 이동 ***(setOnClickListener)***
```kotlin
// 회원가입
    btn_signup.setOnClickListener{
        val intent = Intent(this, SignUpActivity::class.java)
        startActivityForResult(intent, RESULT_CODE)
    }
```

🔥 모든 EditText에 데이터가 있으면 가입 완료 Toast,
하나라도 빈 칸이 있으면 이를 알리는 Toast. ***(isNullOrBlank())***
```kotlin
btn_signup.setOnClickListener{
    if(et_name.text.isNullOrBlank() || et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
        toast("모든 정보를 기입해주세요.")
    } 
    ...
}
```

🔥 비밀번호 EditText는 그 내용이 특수문자로 나타날 것. ***(inputType)***  
🔥 모든 EditText에 미리보기 문구 존재. ***(hint)***
```xml
<EditText
          ...
        android:hint="비밀번호"
        android:inputType="textPassword"
        />
```
  
  
### 📑 OPTIONAL - 화면이동
🔥 회원가입 후 이전 로그인 화면으로 돌아가기.  
🔥 방금 가입한 아이디와 비밀번호가 미리 입력되어 있을 것.  
**(startActivityForResult, setResult, onActivityResult)**  

**LoginActivity**  
: 데이터를 받아올 수 있도록 startActivityForResult로 SignUpActivity 호출  
  누가 호출했는지 알려주는 RequestCode는 0으로 지정  
```kotlin
val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 0)
```

**SignUpActivity**  
: SharedPreferences에 id, pw를 저장하고,  
 intent에 id와 pw를 각각 "id", "pw"의 이름으로 putExtra로 달아준 뒤 LoginActivity로 복귀  
```kotiln
    btn_signup.setOnClickListener{
        if(et_name.text.isNullOrBlank() || et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
            toast("모든 정보를 기입해주세요.")
        } else{
            // SharedPreferences에 id, pw 저장
            Pref.sharedEdit.putString("id", et_id.text.toString())
            Pref.sharedEdit.putString("pw", et_pw.text.toString())
            Pref.sharedEdit.apply()

            // 로그인 액티비티에 set
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", et_id.text.toString())
            intent.putExtra("pw", et_pw.text.toString())
            toast("가입이 완료되었어요! :)")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
```

**LoginActivity**  
: 가입 후 돌아왔을 때 id와 pw를 각각 getStringExtra를 통해 세팅  
```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                0 -> {
                    et_id.setText(data!!.getStringExtra("id").toString())
                    et_pw.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }

companion object {
    private const val RESULT_CODE = 0
}
```
  
  
### 📑 OPTIONAL - 자동 로그인  
🔥 로그인 시 HomeActivity로 이동 ***(startActivity)***  
```kotlin
// 로그인
        btn_login.setOnClickListener{
            // 자동 로그인 체크 시 SharedPreferences에 저장
            if (switch_auto.isChecked) { ... }

            toast("로그인 성공 :)")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
```

🔥 id, pw를 기억하여 다음 로그인에서 자동 로그인하고 Toast.  
***(SharedPreferences)***  
**Class App**  
: SharedPreferences와 에디터를 생성하는 함수 호출  
```kotlin
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Pref.init(this)
    }
}
```
**object Pref**  
: sharedEdit(에디터) 생성  
```kotlin
object Pref {
    private val PREF_NAME = "pref"

    lateinit var sharedPref : SharedPreferences
    lateinit var sharedEdit : SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    fun init(context: Context){
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedEdit = sharedPref.edit()
        sharedEdit.apply()
    }
}
```
**LoginActivity**  
: 자동 로그인 스위치 On일 시 id, pw를 저장.  
  바디 도입부에 스위치 On/Off를 체크하여 sharedPref.getString한 id, pw가 존재한다면 자동로그인
```kotlin
btn_login.setOnClickListener{
    // id, pw 존재 -> 로그인 성공
    if(et_id.text.toString() == Pref.sharedPref.getString("id", "")
        && et_pw.text.toString() ==  Pref.sharedPref.getString("pw", "")){

        // 자동 로그인 체크 시 그 여부를 SharedPreferences에 저장
        if (switch_auto.isChecked) {
            Pref.sharedEdit.putBoolean("auto", true)
            Pref.sharedEdit.apply()
        }

        toast("로그인 성공 :)")
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

    } else {
        toast("회원 정보를 다시 확인해주세요.")
    }
}
```
onCreate 도입부에 스위치 on/off 여부를 확인하여 이전에 자동 로그인을 체크해뒀다면 바로 HomeActivity 호출
```kotlin
if(Pref.sharedPref.getBoolean("auto", false))
{
    toast("자동 로그인 되었습니다")
    val intent = Intent(this, HomeActivity::class.java)
    startActivity(intent)
}
```


### 📌 Assignment02 20201106
### 📑 MUST - RecyclerView on HomeActivity

<img src = https://user-images.githubusercontent.com/50744222/101046641-8bc90000-35c4-11eb-92a0-cd74b98a9f75.PNG width = 30%/>
<img src = https://user-images.githubusercontent.com/50744222/101046737-9e433980-35c4-11eb-8e55-4317218d485b.PNG width = 30% />

🔥 로그인 시 이동한 홈에 RecyclerView 세팅 ***(setOnClickListener)***  
**HomeActivity**  
: RecyclerView에 Adapter를 달아주고, Adapter의 데이터에 ProfileData클래스 타입의 내용을 달아줌
```kotlin
profileAdapter = ProfileAdapter(requireContext())

rcv_profile.apply{
    adapter = profileAdapter
    layoutManager = LinearLayoutManager(requireContext())
}

profileAdapter.data = mutableListOf(
    ProfileData(
        "이름",
        "이예인",
        "2020-11-25",
        "이름이에요~"
    ),
    ...
)
profileAdapter.notifyDataSetChanged()
```
🔥 각 아이템을 누르면 해당 상세 화면으로 이동
**ProfileViewHolder**  
: 뷰홀더의 onBind 함수 안에 구현. intent의 putExtra를 활용하여 상세 화면 DetailActivity를 호출
```kotlin
fun onBind(data: ProfileData) {
        title.text = data.title
        subTitle.text = data.subTitle

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data.title)
            intent.putExtra("subtitle", data.subTitle)
            intent.putExtra("date", data.date)
            intent.putExtra("extra", data.extra)
            startActivity(itemView.context, intent, null)
        }
    }
```
**DetailActivity**  
: ViewHolder에서 넣어준 내용을 getStringExtra를 통해 받고, 텍스트 뷰에 세팅
```kotlin
val title  = intent.getStringExtra("title")
tv_title.text = title
...
```  

### 📌 Assignment03 20201125
### 📑 MUST - TabLayout and BottomNavigation with Fragments
HomeActivity에는 총 세 개의 Fragment가 달려있고,  
첫번째 Fragment에 또 두 개의 Fragment가 TabLayout과 연동되어 달려있는 형태  

**activity_home.xml**  
: HomeActivity의 xml에 BottomNavigation을 배치하고 checked 여부에 따라 색을 바꿔주는 xml과 내용을 달아주는 memu.xml을 세팅
```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navi"
        app:itemIconTint="@color/bottom_navi_color"
        app:itemTextColor="@color/bottom_navi_color"
        app:menu="@menu/menu"
        ...
        />
```
**bottom_navi_color.xml**
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="#86C3BA" android:state_checked="true" />
    <item android:color="#9E9E9E" android:state_checked="false" />
</selector>
```

**HomeActivity**  
: 매니저를 불러와 어댑터의 변수에 프래그먼트를 생성해 넣어줌
```kotlin
viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)
    viewpagerAdapter.fragments = listOf(
        MeFragment(),
        ProfileFragment(),
        SettingFragment()
    )
    viewpager_home.adapter = viewpagerAdapter
```
뷰페이저 슬라이드 시 기본적으로 작동하는 함수들  
onPageSelected는 한 페이지가 선택됐을 때 BottomNavigation의 check값을 변경
```kotlin
viewpager_home.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {}
    // 뷰페이저의 페이지 중 하나가 선택된 경우
    override fun onPageSelected(position: Int) {
        bottom_navi.menu.getItem(position).isChecked = true
    }
})
```
BottomNavigation을 세팅  
BottomNavigation의 요소가 선택되었을 때 어댑터의 currentItem에 인덱스를 넣어주는 방법으로 연동
```kotlin
bottom_navi.setOnNavigationItemSelectedListener {
    var index by Delegates.notNull<Int>()
    when(it.itemId){
        R.id.menu_me -> index = 0
        R.id.menu_profile -> index = 1
        R.id.menu_setting -> index = 2
    }
    viewpager_home.currentItem = index
    true
}
```

**MeFragment**  
: HomeActivity의 첫번째 Fragment로, TabLayout에 연결된 두 Fragment를 가짐
  
Adapter의 매니저를 달아줄 때 Activity는 supportFragmentManager를 활용하지만,  
프래그먼트에서는 childFragmentManager를 씀을 유의
  ```kotlin
  viewpagerAdapter = ViewPagerAdapter(childFragmentManager)
  ```
TabLayout(tab_me)과 ViewPager(viewpager_heyyou)의 연동
```kotlin
view.tab_me.setupWithViewPager(view.viewpager_heyyou)
        view.tab_me.apply {
            getTabAt(0 )?.text = "Hey"
            getTabAt(1 )?.text = "You"
        }
```

**ProfileFragment**  
: HomeActivity의 두번째 Fragment로, 2차 과제의 RecyclerView를 그대로 가짐

**SettingFragment**
: HomeActivity의 세번째 Fragment

### 📌 Assignment06 20201203
### 📑 MUST - Retrofit
**PostMan 캡처 및 GIF**  
<img src = https://user-images.githubusercontent.com/50744222/101030335-a26b5900-35bc-11eb-9381-c321fb608a6f.PNG 
width = 45% />
<img src = https://user-images.githubusercontent.com/50744222/101032254-2f161700-35bd-11eb-9e06-e5c8ab06c0cb.PNG
width = 45% />  
<img src = https://user-images.githubusercontent.com/50744222/101045076-5bcd2d00-35c3-11eb-9768-c7fe4cd5ecdd.gif width = 30% />
<img src = https://user-images.githubusercontent.com/50744222/101045338-8d45f880-35c3-11eb-8775-3e4afec7ac15.gif width = 30% />  
  
**SoptSercive 인터페이스**  
: 회원가입, 로그인 시 넘길 Headers, POST, Body의 틀   
```kotlin
interface SoptService{
    @Headers("Content-Type: application/json")
    @POST("/users/signup")
    fun postSignUp(
        @Body body : RequestSignUpData
    ) : Call<ResponseSignUpData>
    ...
}
```
**RequestSignUpData, ResponseSignUpData**  
: 통신 시 주고받을 데이터 묶음을 정의한 클래스  
```kotlin
data class RequestSignUpData (
    val email : String,
    val password : String,
    val userName : String
)

data class ResponseSignUpData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}
```
**SignUpActivity**  
: 모든 editText의 내용이 있을 때 call에 그 내용을 담아 통신 요청  
요청이 200~300대로 정상적으로 돌아오면 response.body()에 데이터가 담겨오므로  onResponse() 함수에서 회원가입 완료 처리 후 LoginActivity로 복귀
```kotlin
val email = et_signup_id.text.toString()
    ...
val call : Call<ResponseSignUpData> = SoptServiceImpl.service.postSignUp(
    RequestSignUpData(email = email, password = password, userName = name)
)
call.enqueue(object : Callback<ResponseSignUpData>{
    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
        toast("서버가 불안정합니다.")
    }

    override fun onResponse(
        call: Call<ResponseSignUpData>,
        response: Response<ResponseSignUpData>
    ) {
        response.takeIf { it.isSuccessful }
            ?.body()
            ?.let{ it ->
            // LoginActivity에 putExtra하고 돌아가기
            ...
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
}

})
```

**LoginActivity**  
: email과 password를 담아 보내고 제대로 로그인이 성공하면 onResponse()의 response.body()에 담겨온 데이터를 활용  
자동 로그인 체크 여부를 sharedPreference에 저장하고, HomeActivity를 호출
```kotlin
val password = et_pw.text.toString()
val call : Call<ResponseLoginData> = SoptServiceImpl.service.postLogin(
    RequestLoginData(email = email, password = password)
)
call.enqueue(object : Callback<ResponseLoginData>{
    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
        toast("서버가 불안정합니다.")
    }

    override fun onResponse(
        call: Call<ResponseLoginData>,
        response: Response<ResponseLoginData>
    ) {
        response.takeIf{ it.isSuccessful}
            ?.body()
            ?.let { it ->
                if (switch_auto.isChecked) {
                    Pref.sharedEdit.putBoolean("auto", true)
                    Pref.sharedEdit.apply()
                }

                toast("로그인 성공 :)")
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            } ?: showError(response.errorBody())
    }

})
```