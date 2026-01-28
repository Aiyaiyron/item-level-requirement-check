package com.skilllevelchecker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SkillLevelCheckerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SkillLevelCheckerPlugin.class);
		RuneLite.main(args);
	}
}