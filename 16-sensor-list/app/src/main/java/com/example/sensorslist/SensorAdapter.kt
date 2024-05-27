package com.example.sensorslist

import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SensorAdapter(private val sensors: List<Sensor>) :
    RecyclerView.Adapter<SensorAdapter.SensorViewHolder>() {
    class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sensorName: TextView = itemView.findViewById(R.id.sensorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sensor_item, parent, false)
        return SensorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        val sensor = sensors[position]
        holder.sensorName.text = sensor.name
    }

    override fun getItemCount() = sensors.size
}
