package game

import com.balduvian.cnge.core.util.Color
import com.balduvian.cnge.core.util.Color.Companion.uniformColor
import com.balduvian.cnge.core.util.Font
import com.balduvian.cnge.graphics.Camera

class GameFont(var color: Color) : Font() {
	private val texture = GameResources.fontTiles
	private val shader = GameResources.tileShader
	private val rect = GameResources.rect

	var alpha = 1.0f

	override fun preRender() {
		texture.get().bind()
	}

	override fun renderChar(camera: Camera, char: Char, x: Float, y: Float, width: Float, height: Float) {
		val tileX = char.code % 16
		val tileY = char.code / 16

		shader.get().enable(camera.projectionView, Camera.transform(x, y, width, height))
		shader.get().uniformVector4(0, texture.get().tile(tileX, tileY))
		shader.get().uniformColor(1, color, alpha)
		rect.get().render()
	}
}
