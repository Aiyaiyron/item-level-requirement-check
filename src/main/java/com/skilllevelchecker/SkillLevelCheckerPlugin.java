package com.skilllevelchecker;

import com.google.gson.Gson;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
		name = "Test plugin",
		description = "Shows skill and quest requirements for items in overlays and tooltips."
)
public class SkillLevelCheckerPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private SkillLevelCheckerOverlay overlay;

	@Inject
	private SkillLevelCheckerTooltipOverlay tooltipOverlay;

	@Inject
	private Gson gson;

	private boolean tooltipSetThisFrame = false;

	private WidgetItem lastHoveredItem = null;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Item Requirements started!");
		SkillLevelCheckerData.loadFromJson(gson);
		overlayManager.add(overlay);
		overlayManager.add(tooltipOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Item Requirements stopped!");
		overlayManager.remove(overlay);
		overlayManager.remove(tooltipOverlay);
	}

	public void reloadRequirements()
	{
		SkillLevelCheckerData.reloadRequirements(gson);
		log.info("Item requirements reloaded.");
	}

	@Subscribe
	public void onCommandExecuted(CommandExecuted event)
	{
		String command = event.getCommand();
		if ("reloadreq".equalsIgnoreCase(command))
		{
			reloadRequirements();
		}
	}

	public SkillLevelCheckerTooltipOverlay getTooltipOverlay()
	{
		return tooltipOverlay;
	}

	public void markTooltipSetThisFrame()
	{
		this.tooltipSetThisFrame = true;
	}

	public void resetTooltipFlag()
	{
		this.tooltipSetThisFrame = false;
	}

	public boolean wasTooltipSetThisFrame()
	{
		return this.tooltipSetThisFrame;
	}

	public void updateHoveredItem(WidgetItem currentItem)
	{
		if (lastHoveredItem != null && currentItem != lastHoveredItem)
		{
			log.debug("Stopped hovering item: {}", lastHoveredItem.getId());
		}

		lastHoveredItem = currentItem;
	}

	@Subscribe
	public void onClientTick(ClientTick tick)
	{
		if (!tooltipSetThisFrame)
		{
			tooltipOverlay.clearHoveredTooltip();
		}
		resetTooltipFlag();
	}

	@Provides
	SkillLevelCheckerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillLevelCheckerConfig.class);
	}
}