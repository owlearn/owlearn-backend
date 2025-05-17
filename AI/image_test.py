from service.image_generator import generate_images
import asyncio

prompt = ["owl"]

# 이미지 생성
image_urls = asyncio.run(generate_images(prompt))

print(image_urls)