package fup.edu.pruebamercadoagro

data class ItemProducto(
    val id: Int,
    val prov_ranking: Int,
    val prov_group: String,
    val prov_description: String,
    val prov_status: Int,
    val people_peo_id: Int,
    val created_at: String,
    val updated_at: String,
    val pro_name: String,
    val pro_type: String,
    val pro_price: Int,
    val pro_certs: String,
    val pro_image: Int,
    val pro_unit: String,
    val pro_description: String,
    val pro_status: Int,
    val providers_id: Int,
    val categories_id: Int
)