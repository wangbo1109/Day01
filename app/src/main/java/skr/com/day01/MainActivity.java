package skr.com.day01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.function.Supplier;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.my_image_view)
    SimpleDraweeView myImageView;
    //分配的可用内存
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();

    //使用的缓存数量
    private static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;

    //小图极低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    private static final int MAX_SMALL_DISK_VERYLOW_CACHE_SIZE = 20 * ByteConstants.MB;

    //小图低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    private static final int MAX_SMALL_DISK_LOW_CACHE_SIZE = 60 * ByteConstants.MB;

    //默认图极低磁盘空间缓存的最大值
    private static final int MAX_DISK_CACHE_VERYLOW_SIZE = 20 * ByteConstants.MB;

    //默认图低磁盘空间缓存的最大值
    private static final int MAX_DISK_CACHE_LOW_SIZE = 60 * ByteConstants.MB;

    //默认图磁盘缓存的最大值
    private static final int MAX_DISK_CACHE_SIZE = 100 * ByteConstants.MB;

    //小图所放路径的文件夹名
    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "ImagePipelineCacheSmall";

    //默认图所放路径的文件夹名
    private static final String IMAGE_PIPELINE_CACHE_DIR = "ImagePipelineCacheDefault";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       initView();
    }

    private void initView() {
        Uri uri = Uri.parse("http://wenwen.soso.com/p/20090819/20090819205029-1265245578.jpg");
        myImageView.setImageURI(uri);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:

                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setCornersRadius(50f);
                myImageView.getHierarchy().setRoundingParams(roundingParams);

                break;
            case R.id.btn2:

                RoundingParams rounding = RoundingParams.fromCornersRadius(5f);
                rounding.setRoundAsCircle(true);
                myImageView.getHierarchy().setRoundingParams(rounding);
                break;
            case R.id.btn3:

                myImageView.setAspectRatio(1.2f);
                break;
            case R.id.btn4:
                Uri uri = Uri.parse("http://wenwen.soso.com/p/20090819/20090819205029-1265245578.jpg");
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(myImageView.getController())
                        .build();
                myImageView.setController(controller);
                break;
            case R.id.btn5:



                break;
            case R.id.btn6:
                ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(
                            String id,
                            @Nullable ImageInfo imageInfo,
                            @Nullable Animatable anim) {
                        if (anim != null) {
                            // 其他控制逻辑
                            anim.start();
                        }
                    }
                };
                Animatable animatable = myImageView.getController().getAnimatable();
                if (animatable != null) {
                    animatable.start();
                    // later
                    animatable.stop();
                }
                Uri uri1=Uri.parse("http://p0.so.qhimgs1.com/bdr/200_200_/t01fb8d831a8f403bcc.gif");
                DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                        .setUri(uri1)
                        .setAutoPlayAnimations(true)
                         // 其他设置（如果有的话）
    .build();
                myImageView.setController(controller1);
                break;
            case R.id.btn7:
                break;
            case R.id.btn8:
                break;
        }
    }

}
