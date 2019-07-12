package com.example.tictactoe.sensorsExample

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class SensorsExampleActivity : AppCompatActivity() {


      var a :String = "a"

    var TAG = SensorsExampleActivity::class.java.name
    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor
    var gyroScopeSensor: Sensor? = null
    var typeRotationSensor: Sensor? = null

    lateinit var proximitySensorListener: SensorEventListener
    lateinit var gyroscopeSensorListener: SensorEventListener
    lateinit var typeRotationSensorListener: SensorEventListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.example.tictactoe.R.layout.activity_sensors_example)


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)


        gyroScopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        typeRotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)


        if (proximitySensor == null) {
            Log.i(TAG, "Sensor not available")
            finish()
        }

        if (gyroScopeSensor == null) {
            Log.i(TAG, "Sensor not available")
            finish()
        }


        if (typeRotationSensor == null) {
            Log.i(TAG, "Sensor not available")
            finish()
        }





        proximitySensorListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                Log.i(TAG, accuracy.toString())

            }

            override fun onSensorChanged(event: SensorEvent?) {

                if (event != null) {
                    if (event.values[0] < proximitySensor.maximumRange) {
                        window.decorView.setBackgroundColor(Color.RED)
                    } else {
                        window.decorView.setBackgroundColor(Color.BLUE)
                    }

                }
            }
        }




        gyroscopeSensorListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

            override fun onSensorChanged(event: SensorEvent?) {

                if (event != null) {

                    Log.i(
                        TAG, "event.values[0] :" + event.values[0].toString() + "\n" +
                                "event.values[1] :" + event.values[2].toString() + "\n" +
                                "event.values[2] :" + event.values[2].toString() + "\n"
                    )

                    //anticlockwise
                    if (event.values[2] > 0.5f) {

                        window.decorView.setBackgroundColor(Color.GREEN)
                    } else if (event.values[2] < -0.5f) {
                        window.decorView.setBackgroundColor(Color.BLACK)
                    }
                }
            }
        }


        typeRotationSensorListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

            override fun onSensorChanged(event: SensorEvent?) {

                var rotationMatrix = FloatArray(16)
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event?.values)

                //Remap coordinate system

                var remappedRotationMAtrix = FloatArray(16)

                SensorManager.remapCoordinateSystem(
                    rotationMatrix,
                    SensorManager.AXIS_X,
                    SensorManager.AXIS_Y,
                    remappedRotationMAtrix
                )

                var orientations = FloatArray(3)

                SensorManager.getOrientation(remappedRotationMAtrix, orientations)

                for (i in 0..2) {
                    orientations[i] = Math.toDegrees(orientations[i].toDouble()).toFloat()
                }


                if (orientations[2] > 45) {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                } else if (orientations[2] < -45) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if (Math.abs(orientations[2]) < 10) {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }


            }
        }

    }


    override fun onResume() {
        super.onResume()
//         sensorManager.registerListener(proximitySensorListener,proximitySensor,2*1000*1000)
//
//         sensorManager.registerListener(gyroscopeSensorListener,gyroScopeSensor,SensorManager.SENSOR_DELAY_NORMAL)
//
//
        sensorManager.registerListener(
            typeRotationSensorListener,
            typeRotationSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }


    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(proximitySensorListener)
        sensorManager.unregisterListener(gyroscopeSensorListener)
        sensorManager.unregisterListener(typeRotationSensorListener)

    }
}