# pip install diffusers transformers torch torchvision torchaudio pillow
# pip install accelerate

from pathlib import Path
import uuid
from typing import List
import asyncio

from diffusers import StableDiffusionPipeline
import torch


# 모델 로드 (float32 사용 + CPU 지원)
pipe = StableDiffusionPipeline.from_pretrained(
    "runwayml/stable-diffusion-v1-5",
    torch_dtype=torch.float32
)

device = "cuda" if torch.cuda.is_available() else "cpu"
pipe = pipe.to(device)


def generate_image_sync(prompt: str, output_dir: Path) -> str:
    image = pipe(prompt).images[0]
    filename = f"{uuid.uuid4()}.png"
    path = output_dir / filename
    image.save(path)
    return f"http://localhost:8000/static/images/{filename}"

async def generate_images(prompts: List[str]) -> List[str]:
    output_dir = Path("./static/images")
    output_dir.mkdir(parents=True, exist_ok=True)

    tasks = []
    for prompt in prompts:
        task = asyncio.to_thread(generate_image_sync, prompt, output_dir)
        tasks.append(task)

    image_urls = await asyncio.gather(*tasks)
    return image_urls