package br.com.gabriel.akinmovesp.data

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class VehicleClusterItem(
    val positionBus: LatLng,
    val titleBus: String?,
    val snippetBus: String?
) : ClusterItem {
    override fun getPosition(): LatLng = positionBus
    override fun getTitle(): String? = titleBus

    override fun getSnippet(): String? = snippetBus

    override fun getZIndex(): Float? {
        TODO("Not yet implemented")
    }
}