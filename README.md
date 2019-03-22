# DL10FlipViewPage
一个组合控件翻页式的GridView列表带可定制的圆形页面指示器

说明链接：

# 效果图
![](img/翻页点击item.gif)

# 使用方法

Step 1. Add the JitPack repository to your build file 
Add it in your root build.gradle at the end of repositories:
```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```java
	dependencies {
	        implementation 'com.github.D10NGYANG:DL10FlipViewPage:1.0.0'
	}
```
Step 3. 在布局里添加
记得要有ViewPager + CirclePageIndicator
```java
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <android.support.v4.view.ViewPager
            android:id="@+id/viewPager"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1" />

        <com.dlong.rep.dlflipviewpage.indicator.CirclePageIndicator
            android:id="@+id/indicator"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom"
            android:paddingBottom="10dp"
            app:pageColor="@color/colorAccent"
            app:radius="6dp"
            app:selectColor="@color/colorPrimary"
            app:strokeWidth="0dp" />

    </LinearLayout>
```
CirclePageIndicator可自定义的信息如下

```java
// 圆形指示器是否居中显示，默认为true
app:centered="true"
// 当前页面指示器颜色，默认为#FFFFFFFF
app:selectColor="@color/colorPrimary"
// 所有页面指示器颜色，默认为#00000000
app:pageColor="@color/colorAccent"
// 所有页面指示器外边框颜色，默认为#FFDDDDDD
app:strokeColor="@android:color/white"
// 所有页面指示器外边框宽度，默认为1
app:strokeWidth="0dp"
// 指示器半径
app:radius="6dp"
// 圆形页面指示器是否锁定位置，默认为false。锁定位置的话就不能跟随手指划动而移动指示器
app:snap="false"
```
Step 4. 在活动中
先声明控件

```java
private ViewPager view_pager;
private CirclePageIndicator indicator;
```
获得控件
```java
    private void initview() {
        view_pager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
    }
```
使用默认样式

```java
    private void initDatas() {
        // 新建一个数据列表，DLGridViewBean是默认的实体类
        final List<DLGridViewBean> dataList = new ArrayList<>();
        // 循环存进
        for (int i = 0; i < 22; i++) {
            // 新建一个实体类
            DLGridViewBean bean = new DLGridViewBean();
            // 存放文本信息，会显示在每个item的文本位置
            bean.setText("靓仔"+i);
            // 存放图片信息，会显示在每个item的图片位置
            bean.setImg(R.mipmap.ima);
            // 可以携带一些自定义的信息
            bean.setObject(null);
            // 存进列表
            dataList.add(bean);
        }
        // DLVPSetting是依赖库里的工具，新建他。填进行数和列数，行列都是1的话就是一个页面了。带一个监听器，返回点击item事件
        DLVPSetting setting = new DLVPSetting(mContext, 4, 4, new DLVPSetting.OnClickItemListener() {
            @Override
            public void OnClickItem(int position, DLGridViewBean bean) {
                // 使用默认模式的数据从这里回调
                Toast.makeText(mContext, bean.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnClickItem(int position, Map<String, Object> map) {
                // 使用自定义模式的数据从这里回调
            }
        });
        // 最主要的两步
        // 给viewPager设置适配器，从DLVPSetting里通过工具获得，放进数据列表即可
        view_pager.setAdapter(setting.getAdapter(dataList));
        // 最后绑定圆形适配器就完成了
        indicator.setViewPager(view_pager);
    }
```
使用自定义样式

```java
    private void initDatas2() {
        // 和SimpleAdapter的参数一样的from和to
        String[] from = new String[]{"txt", "img"};
        int[] to = new int[]{R.id.tv_text, R.id.iv_img};
        // 新建数据列表
        final ArrayList<Map<String, Object>> dataList2 = new ArrayList<>();
        // 循环存进
        for (int i = 0; i < 5; i++) {
            Map<String, Object> bean = new HashMap<>();
            bean.put("txt", "家园"+i);
            bean.put("img", R.drawable.home);
            dataList2.add(bean);
        }
        // DLVPSetting是依赖库里的工具，新建他。填进行数和列数，行列都是1的话就是一个页面了。带一个监听器，返回点击item事件
        DLVPSetting setting = new DLVPSetting(mContext, 1, 1, new DLVPSetting.OnClickItemListener() {
            @Override
            public void OnClickItem(int position, DLGridViewBean bean) {
                // 使用默认模式的数据从这里回调
            }

            @Override
            public void OnClickItem(int position, Map<String, Object> map) {
                // 使用自定义模式的数据从这里回调
                Toast.makeText(mContext, (String) map.get("txt"), Toast.LENGTH_SHORT).show();
            }
        });
        // 最主要的两步
        // 给viewPager设置适配器，从DLVPSetting里通过工具获得，放进数据列表、自定义item样式、from和to
        view_pager.setAdapter(setting.getAdapter(dataList2, R.layout.item_main_mode, from, to));
        // 最后绑定圆形适配器就完成了
        indicator.setViewPager(view_pager);
    }
```
完事。
