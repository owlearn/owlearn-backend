import uuid
from pathlib import Path

async def generate_images(image_prompts: list[str]) -> list[str]:
    image_urls = []
    # for i, prompt in enumerate(image_prompts):
    #     # 예: stable diffusion 호출 후 저장
    #     filename = f"{uuid.uuid4()}.png"
    #     save_path = Path(f"./static/images/{filename}")
    #
    #     # 여기에 Stable Diffusion 호출 및 저장 로직
    #     # 예: image.save(save_path)
    #
    #     # URL로 응답
    #     image_urls.append(f"https://yourdomain.com/static/images/{filename}")

    return image_urls