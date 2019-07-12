package com.example.tictactoe.camera_without_preview

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_camera_preview.*
import java.lang.Exception
import java.util.*


class CameraPreviewActivity: AppCompatActivity() {

    val TAG = CameraPreviewActivity::class.java.name
    val NEXT = 2

    companion object{
        val DONE = 1

        val PERIOD:Long = 1
    }
    lateinit var threadHandler: Handler

    var camera: Camera? = null

    var cameraId = -1
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_camera_preview)




        //do we have camera in the device
        if(!(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))){
            Toast.makeText(this,"No camera available",Toast.LENGTH_SHORT).show()

        }else{
            cameraId = findFrontFacingCamera()
        }


        if(cameraId < 0){
            Toast.makeText(this,"No front camera",Toast.LENGTH_SHORT).show()
        }else{
            safeCameraOpen(cameraId)
        }


        //Fake surface to trick camera

        var surfaceView = SurfaceTexture(10)


        try{

            camera?.setPreviewTexture(surfaceView)




        }catch (e:Exception)
        {
            e.printStackTrace()
        }


        camera?.startPreview()

        var params = camera?.parameters


        if (params != null) {
            params.jpegQuality = 100
        }


        if(camera != null){
            camera!!.parameters = params

        }


        val mCall = object : Camera.PictureCallback {
            override fun onPictureTaken(data: ByteArray?, camera: Camera?) {



                //set image to imageview

                var bitmapPicture = BitmapFactory.decodeByteArray(data,0, data!!.size)


                ivCameraPreview.setImageBitmap(bitmapPicture)

                Message.obtain(threadHandler,NEXT,"").sendToTarget()



            }

        }

        threadHandler = object : Handler() {
            override fun handleMessage(msg: Message?) {

                when(msg?.what){

                    DONE -> camera?.takePicture(null,null,mCall)

                    NEXT ->
                        timer = Timer(applicationContext,threadHandler)

            }
        }

        }


        var timer = Timer(applicationContext,threadHandler)
        timer.execute()


        }


            //threadhandler



    private fun safeCameraOpen(cameraId: Int):Boolean {


        var qOpened = false

        try{
            releaseCamera()
            camera = Camera.open(cameraId)

            qOpened = (camera != null)
        }catch (e:Exception){
            e.printStackTrace()
        }


        return qOpened
    }

    private fun releaseCamera() {

        if (camera != null) {
            camera!!.stopPreview();
            camera!!.release();
            camera = null;
        }
    }

    private fun findFrontFacingCamera(): Int {

        var cameraId = -1
         var numberOfCameras = Camera.getNumberOfCameras()

        for(i in 0..numberOfCameras-1)
        {
            var cameraInfo = Camera.CameraInfo()

            Camera.getCameraInfo(i,cameraInfo)

            if(cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT){
                Log.i(TAG,"Camera Found")

                cameraId = i

                break
            }
        }

        return cameraId

    }
}