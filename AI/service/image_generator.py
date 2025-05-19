# pip install diffusers transformers torch torchvision torchaudio pillow accelerate

from pathlib import Path
import uuid
from typing import List
import asyncio
import threading

from diffusers import StableDiffusionPipeline
from diffusers import DPMSolverMultistepScheduler
import torch

# 모델 로드
pipe = StableDiffusionPipeline.from_pretrained(
    "runwayml/stable-diffusion-v1-5",
    torch_dtype=torch.float32
)

# 디바이스 설정
device = "cuda" if torch.cuda.is_available() else "cpu"
pipe = pipe.to(device)

# 속도 향상을 위한 최적화
pipe.scheduler = DPMSolverMultistepScheduler.from_config(pipe.scheduler.config)
pipe.enable_attention_slicing()

# 이미지 생성 중 중복 방지를 위한 락
pipe_lock = threading.Lock()

async def generate_images(prompts: List[str]) -> List[str]:
    return await asyncio.to_thread(generate_images_sync, prompts)

def generate_images_sync(prompts: List[str]) -> List[str]:
    print("이미지 생성 중...")
    output_dir = Path("./static/images")
    output_dir.mkdir(parents=True, exist_ok=True)

    with pipe_lock:
        generator = torch.Generator(device).manual_seed(0)
        images = pipe(prompts, generator, num_inference_steps=20).images

    image_urls = []
    for image in images:
        filename = f"{uuid.uuid4()}.png"
        path = output_dir / filename
        image.save(path)
        image_urls.append(f"http://localhost:8000/static/images/{filename}")

    return image_urls