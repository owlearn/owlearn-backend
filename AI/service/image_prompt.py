def refine_image_prompt(raw_prompt: str, style: str) -> str:
    # Stable Diffusion 호환용 스타일 가이드 추가
    return (
        f"A detailed illustration of the scene: \"{raw_prompt}\". "
        f"Children's book style, warm atmosphere, "
        f"drawing style: {style}"
    )


def generate_final_image_prompts(raw_prompts: list[str], style: str) -> list[str]:
    return [refine_image_prompt(p, style) for p in raw_prompts]
