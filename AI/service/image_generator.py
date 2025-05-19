# pip install diffusers transformers torch torchvision torchaudio pillow
# pip install accelerate

from pathlib import Path
import uuid
from typing import List
import asyncio
import threading

from diffusers import StableDiffusionPipeline
import torch

# 모델 로드
pipe = StableDiffusionPipeline.from_pretrained(
    "runwayml/stable-diffusion-v1-5",
    torch_dtype=torch.float32
)

# 디바이스 설정
device = "cuda" if torch.cuda.is_available() else "cpu"
pipe = pipe.to(device)

# 이미지 생성 중 중복 방지를 위한 락
pipe_lock = threading.Lock()

def generate_image_sync(prompt: str, output_dir: Path) -> str:
    with pipe_lock:
        image = pipe(prompt).images[0]
    filename = f"{uuid.uuid4()}.png"
    path = output_dir / filename
    image.save(path)
    return f"http://localhost:8000/static/images/{filename}"

async def generate_images(prompts: List[str]) -> List[str]:
    output_dir = Path("./static/images")
    output_dir.mkdir(parents=True, exist_ok=True)

    image_urls = []
    for prompt in prompts:
        # 하나씩 순차적으로 실행
        url = await asyncio.to_thread(generate_image_sync, prompt, output_dir)
        image_urls.append(url)

    return image_urls